package LottoService;

import java.util.List;

public class Lottery {

	private List<Integer> lottoNumber;

	public Lottery(List<Integer> lottoNumber) {
		super();
		this.lottoNumber = lottoNumber;

	}

	// 로또 순위 확인
	public int check(List<Integer> userNumber) {
		int cnt = 0;
		for (int num : userNumber) {
			if (lottoNumber.contains(num)) {
				cnt++; // 5
			}
		}

		if (cnt == 0) {
			return 5;
		}

		return 6 - cnt;
	}
}
