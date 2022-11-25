package homework_w3_sa;

public class PublicBus extends PublicTransport {
    //필드 - 부모클래스 상속
    //현재 승객수도 택시, 버스 모두 사용되기에 부모 클래스에 선언함

    //생성자
    public PublicBus(int busNum) {
        this.transportNumber = busNum;
        this.maxPassenger = 30;
        this.transportStatus = status.DRIVING;
        this.fee = 1000;
        StartEngine();
    }

    //메서드
    //1. 운행시작
    void StartEngine(){
        transportStatus = status.DRIVING;

        System.out.println("");
        System.out.println("환영합니다. " + transportNumber + "번 버스 운행자님^^");
        System.out.println("오늘도 힘차게 살려봅시다\n");
        System.out.println("[" + transportNumber + "번 버스 정보]");
        System.out.println("최대 허용 승객수 : " + maxPassenger + "명");
        System.out.println("현재 승객 수 : " + nowPassenger + "명");
        System.out.println("버스 요금 : " + fee + "원");
        System.out.println("현재 연료량 : " + fuel);
        System.out.println("현재 속도 : " + speed + " km/h");
        System.out.println("현재 버스상태 : " + transportStatus);
        System.out.println("\n🚌 부릉~ 부르릉~ 부릉부릉~ 덜덜덜덜덜");

    }


    //2. 버스 상태 변경 메서드
    void ChangeStatus() {
        System.out.println("");
        //버스 상태 체크 및 변환
        if (transportStatus == status.DRIVING) {
            transportStatus = status.PARKING;
            speed = 0;
            System.out.println("상태 = 차고지행(Parking)");
        } else if (transportStatus == status.PARKING) {
            transportStatus = status.DRIVING;
            System.out.println("운행(Driving)");
        }
    }

    //3. 승객 탑승 메서드 - 하차는 따로 구현 안함
    void PassengerBoarding(int passengers) {

        //버스 상태 체크(Driving일 경우만 탑승 가능)
        if (transportStatus == status.DRIVING) {
            int checkPassenger = nowPassenger + passengers;
            //승객 인원 체크
            if (nowPassenger < maxPassenger && maxPassenger >= checkPassenger && passengers > 0) {
                nowPassenger += passengers;
                int feeSum = fee * passengers;
                System.out.println("");
                System.out.println("탑승 승객 수 = " + nowPassenger );
                System.out.println("잔여 승객 수 = " + (maxPassenger - nowPassenger));
                System.out.println("요금 확인 = " + feeSum);
            } else if (nowPassenger >= maxPassenger || maxPassenger < checkPassenger) {
                System.out.println("");
                System.out.println("최대 승객 수 초과");
                System.out.println("🤬: 버스 적당히 태우세요!");
                System.out.println("😤: 맞아 맞아");
                System.out.println("😱🥶🥵🤢: #@$%^$#!@$%");
                System.out.println("😬: 죽겄네 아주!!");
            }
        } else if (transportStatus == status.PARKING) {
            System.out.println("");
            System.out.println("죄송합니다. 현재 버스 상태는 " + transportStatus + "입니다.");
            System.out.println("승객은 DRIVING 상태에서만 탑승 가능합니다.");
        }
    }

    public static void main(String[] args) {
        PublicBus bus1 = new PublicBus(1);
        PublicBus bus256 = new PublicBus(256);

        bus1.ChangeStatus(); //운행 -> 주차상태
        bus1.StartEngine(); //주차 -> 운행상태

        bus1.PassengerBoarding(2);

        bus1.Oiling(-50);

        bus1.ChangeStatus(); //운행 -> 주차상태
        bus1.Oiling(10);

        bus1.ChangeStatus();

        bus1.PassengerBoarding(45);

        bus1.PassengerBoarding(5); //25명 아니라 23명...

        bus1.Oiling(-55);
    }
}
