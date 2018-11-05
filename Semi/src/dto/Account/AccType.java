package dto.Account;

public enum AccType {
//	항공료
//	교통
//	숙박
//	입장료
//	음식
//	오락
//	쇼핑
//	기타
	airfare("항공료"), traffic("교통"), stay("숙박"),
	admission("입장료"), food("음식"), play("오락"),
	shop("쇼핑"), etc("기타");
	
    final private String name;
    
    private AccType(String name) { //enum에서 생성자 같은 역할
        this.name = name;
    }
    public String getName() { // 문자를 받아오는 함수
        return name;
    }
    public static AccType getValue(int idx) {
        return AccType.values()[idx - 1];
    }
    public int getIdx() {
        return ordinal() + 1;
    }
    
}
