package homework_w3_sa;

public class PublicTransport {
    //필드
    int transportNumber; //번호
    int fuel = 100; //주유량
    int speed = 0; //속도
    int maxPassenger; //최대 승객수
    int nowPassenger = 0; //현재 승객수
    int fee; //요금

    enum status { //상태 - 운행, 주차
        DRIVING, PARKING,  GENERAL, UNAVAILABLE;
    }
    status transportStatus;


    //생성자 - 기본생성자 사용
    //메서드
    //1. 운행시작
    void StartEngine(){

    }
    //2. 속도변경
    void ChangeSpeed(){

    }
    //3. 상태변경
    void ChangeStatus(){

    }
    //4. 승객탑승
    void PassengerBoarding(){

    }

    //5. 주유
    void Oiling(int fuel){
        System.out.println("");
        this.fuel += fuel;
        System.out.println("주유량 = " + this.fuel);

        if (this.fuel < 10) {
            System.out.println("⛽️주유가 필요합니다.");
            this.ChangeStatus();
        }
    }
}