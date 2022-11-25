package homework_w3_sa;

public class Bus {
    //필드
    int maxPassenger; //최대 승객수 20명
    int nowPassenger; //현재 승객수
    int fee; //요금 1000원
    int busNum; //버스번호
    int fuel; //연료량
    int nowSpeed; //현재 속도
    enum status { //상태 - 운행, 차고지행
        DRIVING, PARKING;
    }

    status busStatus; //버스 상태 체크용 필드

    //생성자
    public Bus(int busNum) {
        maxPassenger = 20;
        nowPassenger = 0;
        fee = 1000;
        this.busNum = busNum;
        fuel = 50;
        nowSpeed = 0;
        busStatus = Bus.status.DRIVING;
        System.out.println("환영합니다. " + busNum + "번 버스 운행자님^^");
        System.out.println("오늘도 힘차게 살려봅시다\n");
        System.out.println("[" + busNum + "번 버스 정보]");
        System.out.println("최대 허용 승객수 : " + maxPassenger + "명");
        System.out.println("현재 승객 수 : " + nowPassenger + "명");
        System.out.println("버스 요금 : " + fee + "원");
        System.out.println("현재 연료량 : " + fuel);
        System.out.println("현재 속도 : " + nowSpeed + " km/h");
        System.out.println("현재 버스상태 : " + busStatus);
        System.out.println("\n🚌 부릉~ 부르릉~ 부릉부릉~ 덜덜덜덜덜");
    }

    //메서드
    //버스 상태 변경 메서드
    void ChangeStatus() {
        System.out.println("");
        System.out.println("[" + busNum + "번 버스 현재 상태]");
        //버스 상태 체크 및 변환
        if (busStatus == status.DRIVING) {
            busStatus = status.PARKING;
            nowSpeed = 0;
            System.out.println("버스 상태 : 운행(Driving) -> 차고지행(Parking)");
        } else if (busStatus == status.PARKING) {
            busStatus = status.DRIVING;
            System.out.println("버스 상태 : 차고지행(Parking) -> 운행(Driving)");
        }

        //연료 체크 - 10미만일 경우 경고 알림
        if (fuel < 10) {
            System.out.println("⛽️주유가 필요합니다.");
        }
    }

    //승객 탑승 메서드
    void PassengerBoarding() {

        //버스 상태 체크(Driving일 경우만 탑승 가능)
        if (busStatus == status.DRIVING) {
            //승객 인원 체크
            if (nowPassenger < maxPassenger) {
                nowPassenger++;
                System.out.println("");
                System.out.println("승객 탑승! 🚌 ⬅️🚶🏻");
                System.out.println("현재 인원: " + nowPassenger + "명");
            } else if (nowPassenger >= maxPassenger) {
                System.out.println("");
                System.out.println("죄송합니다. 더 이상 승객을 태울 수 없습니다.");
                System.out.println("현재 인원 : " + nowPassenger + "명");
                System.out.println("최대 인원 : " + maxPassenger + "명");
                System.out.println("🤬: 버스 적당히 태우세요!");
                System.out.println("😤: 맞아 맞아");
                System.out.println("😱🥶🥵🤢: #@$%^$#!@$%");
                System.out.println("😬: 죽겄네 아주!!");
            }
        } else if (busStatus == status.PARKING) {
            System.out.println("");
            System.out.println("죄송합니다. 현재 버스 상태는 " + busStatus + "입니다.");
            System.out.println("승객은 DRIVING 상태에서만 탑승 가능합니다.");
        }
    }

    //속도 증가 메소드, 매개변수 speed는 증가치
    void SpeedUp(int speed) {
        System.out.println("");
        String speedMode = "up";

        boolean res = false;
        int checkFuel = fuel - speed;

        //현재 주행상태 및 연료량 체크
        if (busStatus == status.DRIVING & fuel > 9) {
           res = DrivingCheck(speed, speedMode);
           if (res == true) {
               System.out.println("가즈아~~~~!!! 🦅🔥🔥🔥 🚌💨💨💨");
           }
        } else {
            System.out.println("🚫속도 변경이 불가능합니다.🚫");
            System.out.println("현재 버스 상태 : " + busStatus);
            System.out.println("현재 연료량 : " + fuel);
            System.out.println("PARKING 상태 또는 연료가 10미만일 경우 속도상승이 안됩니다.");
        }

    }

    //속도 감소 메소드, 매개변수 speed는 감소치
    void SpeedDown(int speed){
        System.out.println("");
        String speedMode = "down";

        boolean res = false;
        //현재 주행상태 및 연료량 체크(연료가 0이 아니면 감속 가능)
        if (busStatus == status.DRIVING && fuel > 0 && nowSpeed > 0) {
            res = DrivingCheck(speed, speedMode);
            if (res == true) {
                System.out.println("워~ 워~ 🙌🙌🙌");
            }
        } else {
            System.out.println("🚫속도 변경이 불가능합니다.🚫");
            System.out.println("버스 상태 및 연료, 속도 를 확인하세요");
            System.out.println("현재 버스 상태 : " + busStatus);
            System.out.println("현재 연료량 : " + fuel);
            System.out.println("현재 속도 : " + nowSpeed);
        }
    }

    //속도 증감속 체크 보조 메서드
    boolean DrivingCheck(int speed, String speedMode) {
        System.out.println("현재 연료량 : " + fuel);
        System.out.println("현재 속도 : " + nowSpeed + " km/h");

        //현재 연료량 체크
        //연료량vs증가치, 현재속도vs증가치
        if (speedMode.equals("up") && fuel < speed) { //(거부)1-1. 속도 증가, 연료 < 증가치
            System.out.println("연료가 부족합니다!");
            return false;
        } else if (speedMode.equals("up") && fuel >= speed) { //(허용)1-2. 속도 증가, 연료 >= 증가치
            fuel -= speed;
            nowSpeed += speed;
            System.out.println("속도 증가 : " + nowSpeed + " km/h");
            System.out.println("현재 연료량 : " + fuel);
            return true;
        } else if (speedMode.equals("down") && nowSpeed < speed) { //(거부)2-1. 속도 감소, 현재속도 < 감소치
            System.out.println("요구한만큼 감속할 수 없습니다.");
            return false;
        }else if (speedMode.equals("down") && nowSpeed >= speed) { //(허용)2-2. 속도 감소, 현재속도 >= 감소치
            fuel--;
            nowSpeed -= speed;
            System.out.println("속도 감소 : " + nowSpeed + " km/h");
            System.out.println("현재 연료량 : " + fuel);
            return true;
        } else { //3. 기타 오류
            System.out.println("알 수 없는 오류, 관리자에게 연락!");
            return false;
        }
    }

    public static void main(String[] args) {
        //(요구사항)버스 번호
        Bus bus1 = new Bus(1);

        //(요구사항)버스 상태 변경 체크 & 승객탑승
        bus1.ChangeStatus();
        bus1.PassengerBoarding();

        bus1.ChangeStatus();
        bus1.PassengerBoarding();

        for (int i = 0; i < 20; i++){
            bus1.PassengerBoarding();
        }

        //(요구사항)속도변경 - 증가
        bus1.SpeedUp(10); //연료 50 -> 40
        bus1.SpeedUp(20); //연료 40 -> 20
        bus1.SpeedUp(15); //연료 20 -> 5
        bus1.SpeedUp(10); //연료 5, 속도 상승은 연료가 최소 10이여야 가능

        bus1.ChangeStatus(); //운행 -> 차고지행, 연료가 10미만이라 주유 알림 발생
        bus1.SpeedUp(1); //차고지행 상태라 속도변경 불가
        bus1.SpeedDown(5); //차고지행 상태라 속도 감소 사용도 불가

        System.out.println("===========");

        //(요구사항)속도변경 - 감속
        Bus bus2 = new Bus(2);
        bus2.ChangeStatus(); //운행 -> 차고지행
        bus2.SpeedDown(10); //속도 0 이므로 감속 불가

        bus2.ChangeStatus(); //차고지행 -> 운행
        bus2.SpeedDown(10); //속도 0이므로 감속 불가

        bus2.SpeedUp(20); //속도 0 -> 20 가능
        bus2.SpeedDown(10); //속도 20 -> 10 가능, 연료량 30 -> 29
        bus2.SpeedDown(10); //속도 10 -> 0 가능, 연료량 29 -> 28
        bus2.SpeedDown(10); //속도 0 -> -10 불가능, 연료량 28 변동 없음

        bus2.SpeedUp(26); //속도 0 -> 26 가능, 연료량 28 -> 2
        bus2.SpeedDown(16); //속도 26 -> 10 가능, 연료량 2 -> 1
        bus2.SpeedDown(5); //속도 10 -> 5 가능, 연료량 1 -> 0
        bus2.SpeedDown(5); //연료 0이므로 불가

        bus2.ChangeStatus(); //운행 -> 차고지행, 주유 필요 알림
        //연료 0이면 운행 -> 차고지행도 못하는데.. 일단 여기까지 만듬


    }
}
