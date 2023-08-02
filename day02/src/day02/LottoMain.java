package day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import LottoService.Lottery;
import LottoService.Machine;

public class LottoMain {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Long peopleNum = 5000000L;
		int lottoType = 0;
		String lottoWay = "auto"; 

		System.out.println("복권 사세요 ! ");
		System.out.println("1. 복권 타입을 지정해주세요. ex. 645");
		
		lottoType = sc.nextInt();
				
		System.out.println("2. 수동/자동 중 골라주세요. 수동은 1 , 자동은 2 입니다.");
			
		if (sc.nextInt() == 1) {
			lottoWay = "manual";
		}
			
		Machine machine = new Machine(lottoType);
		int[] result = new int[6];
	
		// 당첨 번호 지정
		List<Integer> lottoNumber = machine.genernator();
		Lottery lottery = new Lottery(lottoNumber);
	
		// 나의 번호 지정
		List<Integer> myLotto = new ArrayList<Integer>();
	
		if (lottoWay.equals("auto")) { 
			myLotto = machine.genernator();
	
		} else { // 수동
			System.out.println("3. 번호를 하나씩 입력해주세요.");

			while (myLotto.size() < lottoType / 100) {
				int num = sc.nextInt();
				if ((!myLotto.contains(num)) && 0 < num && num <= lottoType % 100) {
					myLotto.add(num);
				} else {
					System.out.println("번호를 잘 못 입력 하셨습니다. 다시 입력 해주세요 ");
				}
			}

		}
		
		sc.close();
		
		System.out.println("고객님의 로또 번호 : " + myLotto);

		int myRank = lottery.check(myLotto);
		result[myRank]++;
	
		// 타인 번호 지정
		for( int i = 0;i<peopleNum-1;i++) {
			List<Integer> lotto = machine.genernator();
	
			int rank = lottery.check(lotto);
			result[rank]++;
		}
	
		Long[] prizeArray = getPrize(result, peopleNum);
		System.out.println("이번 주 로또 번호 : " + lottoNumber);
		
		System.out.println("당첨 금액 : "+prizeArray[myRank] +"원"); // 입력 받기 전까지 쓰레드 멈춤
	}


	public static Long[] getPrize(int[] result, Long peopleNum) {
		
		Long[] prizePrice = new Long [6];
		
		int totalPrice = (int) (peopleNum * 5000 * 0.5 - (result[3] + result[4] + result[5])); 
		
		prizePrice[0] = (long) (totalPrice * 0.75 / result[0]); 
		prizePrice[1] = (long) (totalPrice * 0.125 / result[1]);
		prizePrice[2] = (long) (totalPrice * 0.125 / result[2]);
		prizePrice[3] = 50000L;
		prizePrice[4] = 5000L;
		prizePrice[5] = 0L;

		return prizePrice;
	
	}

}


