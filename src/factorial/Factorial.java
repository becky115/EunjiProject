package factorial;


public class Factorial {
	
	
	public void execute(int maxCount) {
		String result = "1";
		String nextNum = "1";
		
		for(int i=2; i<=maxCount; i++){
			String num2 = "0";
			result = nextNum.toString();
			for(int j=i; j>= 1; j--){
				num2 = plus(num2.toString(), nextNum);
			}
			
			String a = nextNum.toString();
			String b = nextNum.toString();
			for(int k=1; k<i; k++){
				result = a = plus(a, b);
			}

			nextNum = num2;
		}
		System.out.println(result);
		System.out.println(result.length());
//		93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000
//		158
	}

	public String plus(String num1, String num2){
		
		if(num2.length() > num1.length()){
			String num3 = num1;
			num1 = num2;
			num2 = num3;
		}

		String[] number1 = num1.split("");
		String[] number2 = num2.split("");
		
		return calculate(number1, number2);
		
	}

	private String calculate(String[] number1, String[] number2) {
		Integer index = 0;
		
		Integer carryNum = 0;
		boolean check = false;
		for(int i=number1.length-1; i>=0; i--){
			if(carryNum > 0){//이전 자릿수에서 올라온 숫자가 있으면 뒤에서 부터 다시 올려주기
				for(int j=i; j>=0; j--){
					Integer num = Integer.parseInt(number1[j]);
					Integer sum = plus(num, carryNum);
					carryNum = checkCarry(sum);

					if(carryNum > 0){
						number1[j] = String.valueOf((sum - 10));
						if(j == 0){
							check = true;
						}
					}else{
						number1[j] = String.valueOf(sum);
					}
					
				}
				
			}
			
			Integer num1 = Integer.parseInt(number1[i]);
			Integer pointer = (number2.length - 1) - index;
			if(pointer >= 0){
				Integer num2 = Integer.parseInt(number2[pointer]); //뒤에서부터 계산
				Integer sum = plus(num1, num2);
				carryNum = checkCarry(sum);
				
				if(carryNum > 0){
					number1[i] = String.valueOf((sum - 10));
					if(i == 0){
						check = true;
					}
				}else{
					number1[i] = String.valueOf(sum);
					carryNum = 0;
				}
				
				index++;
			}
		
		}

		String preNum = "";
		if(check){
			preNum  = "1";
		}
		return result(preNum, number1);
	

		
	}
	
	private static String result(String preNum, String[] number1) {
		String result = "";
		result += preNum;
		for(int i=0; i <number1.length; i++){
			result += number1[i];
			
		}
//		System.out.println("result: "+ result);
//		System.out.println("len:  " + result.length());
//		System.out.println("--------------------------------");
		return result;
	}


	private Integer plus(Integer num1, Integer num2){
		return num1 + num2;
	}
	
	private Integer checkCarry(Integer num){
		if( num >= 10) return 1;
		return 0;
	}
	

	public static void main(String[] args) {
		Factorial multi = new Factorial();
		multi.execute(100);
		
		String result = multi.plus("5", "10");
		System.out.println(result);
		result = multi.plus("111111111111111115", "999999999999910");
		System.out.println(result);
	}

}
