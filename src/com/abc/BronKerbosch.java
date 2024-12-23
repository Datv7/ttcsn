package com.abc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BronKerbosch {
	
	public static List<Set<Character>> findMaximalCliques(Map<Character, Set<Character>> graph) {
		
		//khởi tạo tham số
		List<Set<Character>> cliques=new ArrayList<Set<Character>>();
		Set<Character> r= new HashSet<Character>(); //r rỗng
		Set<Character> p= new HashSet<Character>(graph.keySet()); //p là tập các đỉnh trong đồ thị
		Set<Character> x= new HashSet<Character>(); //x rỗng
		
		
		bronKerbosch(r, p, x, cliques, graph);
		return cliques;
		
	}
	private static void bronKerbosch(Set<Character> r,Set<Character> p,Set<Character> x,
			List<Set<Character>> cliques,Map<Character, Set<Character>> graph) {
		
		// kiểm tra dừng khi p và x đồng thời rỗng
		if(p.isEmpty() && x.isEmpty()) {
			if(r.isEmpty()) return;
			cliques.add(r);
			return;
		}
		
		Set<Character> temp_p=new HashSet<Character>(p);
		for(Character c:temp_p) {
			
			Set<Character> r2=new HashSet<Character>(r);
			r2.add(c); //thêm đỉnh tiềm năng vào tệp các đỉnh đang xét
			
			Set<Character> p2=new HashSet<Character>(p);
			p2.retainAll(graph.get(c)); //cắt tỉa nhánh bằng cách thu nhỏ tập đỉnh tiềm năng = phép giao với tập hợp các đỉnh kề
			
			Set<Character> x2=new HashSet<Character>(x);
			x2.retainAll(graph.get(c)); //cắt tỉa nhánh bằng cách lưu lại những đỉnh đã xét = phép giao với tập hợp các đỉnh kề
			
			bronKerbosch(r2, p2, x2, cliques, graph);//đệ quy với tham số mới

			p.remove(c); //quay lui với việc loại bỏ đỉnh vừa xét khỏi tập đỉnh tiềm năng
			x.add(c); //quay lui với việc thêm đỉnh vừa xét vào tập đỉnh đã xét
		}
	}
}
