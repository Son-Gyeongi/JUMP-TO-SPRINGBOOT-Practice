package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 없는 속성은 생성자에 포함되지 않는다.
@Getter
//@Setter
public class HelloLombok {

    private final String hello; //  final은 한번 설정한 값을 변경할수 없게 하는 키워드
    private final int lombok;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("헬로", 5);
//        helloLombok.setHello("헬로");
//        helloLombok.setLombok(5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}
