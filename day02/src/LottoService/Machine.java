package LottoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Machine {
	
	private int lottoType;
	
	public Machine(int lottoType) {
		
		super();
		this.lottoType = lottoType;
		
	}
	
	public List<Integer> genernator() {
		
		
		Random random = new Random();
		
		int type = lottoType/100;
		
		// 당첨 번호
		List<Integer> lottoNumber = new ArrayList<Integer>();
		
		
		while (lottoNumber.size() < type) {
			
			int num = random.nextInt(lottoType%100)+1;
			
			if (lottoNumber.contains(num)) {
				continue;
			}
			lottoNumber.add(num);
		}
		
		return lottoNumber;
		
	}
	
	
	
	
	

}
