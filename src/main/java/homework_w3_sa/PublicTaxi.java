package homework_w3_sa;

public class PublicTaxi extends PublicTransport{
    //필드
    String destination = "부자"; //목적지
    int basicDistance; //기본 거리
    int farePerDistance; //거리당 요금
    int fianlFare = 0; //누적요금 계산용 필드
    int finalIncome = 0; // 누적수입

    //생성자
    public PublicTaxi(int taxiNum) {
        this.transportNumber = taxiNum;
        this.maxPassenger = 4;
        this.fee = 3000;
        farePerDistance = 1000;
        basicDistance = 1;
        this.transportStatus = status.GENERAL;
        StartEngine();
    }
    //메서드
    //1. 운행시작
    void StartEngine(){
        transportStatus = status.GENERAL;
        finalIncome = 0;

        System.out.println("");
        System.out.println("환영합니다. " + transportNumber + "번 택시 운행자님^^");
        System.out.println("오늘도 힘차게 살려봅시다\n");
        System.out.println("[" + transportNumber + "번 택시 정보]");
        System.out.println("최대 허용 승객수 : " + maxPassenger + "명");
        System.out.println("현재 승객 수 : " + nowPassenger + "명");
        System.out.println("택시 기본 요금 : " + fee + "원");
        System.out.println("택시 기본 거리 : " + basicDistance + "km");
        System.out.println("택시 거리당 요금 : " + farePerDistance);

        System.out.println("현재 연료량 : " + fuel);
        System.out.println("현재 속도 : " + speed + " km/h");
        System.out.println("현재 택시상태 : " + transportStatus);
        System.out.println("\n🚕 오늘도 돈 벌어보즈아~");
    }

    //2. 운행상태 변경
    void ChangeStatus(){
        System.out.println("");
        //택시 상태 체크 및 변환
        if (transportStatus == status.GENERAL) {
            transportStatus = status.UNAVAILABLE;
            speed = 0;
            nowPassenger = 0;
            System.out.println("상태 = 운행불가(Unavailable)");
            FinalTaxiFare();
            System.out.println("최종 수입 = " + finalIncome + "원");
            System.out.println("수고했어 오늘도~ 🍗🍻");
        } else if (transportStatus == status.UNAVAILABLE) {
            transportStatus = status.GENERAL;
            System.out.println("일반(General)");
        }
    }

    //3. 승객 탑승 메서드
    void PassengerBoarding(int passengers, String destination, int distance) {
        fianlFare = 0;
        //택시 상태 체크(GENERAL 경우만 탑승 가능)
        if (transportStatus == status.GENERAL) {
            int checkPassenger = nowPassenger + passengers;
            //승객 인원 체크
            if (nowPassenger < maxPassenger && maxPassenger >= checkPassenger && passengers > 0) {
                nowPassenger += passengers;
                fianlFare = calcFee(distance);

                System.out.println("");
                System.out.println("탑승 승객 수 = " + nowPassenger );
                System.out.println("잔여 승객 수 = " + (maxPassenger - nowPassenger));
                System.out.println("기본 요금 확인 = " + fee);
                System.out.println("목적지 = " + destination);
                System.out.println("목적지까지 거리 = " + distance + "km");
                System.out.println("지불할 요금 : " + fianlFare);
                System.out.println("상태 : " + transportStatus);
            } else if (nowPassenger >= maxPassenger || maxPassenger < checkPassenger) {
                System.out.println("");
                System.out.println("최대 승객 수 초과");
                System.out.println("🤬: 왜 5명 안되는거야!");
                System.out.println("😤: 맞아 맞아");
                System.out.println("😱🥶: 악!! 우리 늦겠어!!");
                System.out.println("😬: 야야야 빨리, 버스나 타자");
            }
        } else if (transportStatus == status.PARKING) {
            System.out.println("");
            System.out.println("죄송합니다. 현재 택시 상태는 " + transportStatus + "입니다.");
            System.out.println("승객은 GENERAL 상태에서만 탑승 가능합니다.");
        }
    }

    //4.거리당 요금 추가 - 요금계산
    int calcFee(int distance){
        int checkDistance = basicDistance - distance;
        int addFee = 0;

        if (checkDistance >= 0) {
            addFee = fee;
        }
        else if(checkDistance < 0){
            checkDistance *= -1; //양수 전환
            addFee = checkDistance * farePerDistance;
            addFee += fee;
        }

        return addFee;
    }

    //5. 누적 요금 출력
    void FinalTaxiFare(){
        System.out.println("고객 지불 요금 = " + fianlFare);
        finalIncome += fianlFare; //누적 수입
        nowPassenger = 0;
    }

    public static void main(String[] args) {
        PublicTaxi taxi1 = new PublicTaxi(1);
        PublicTaxi taxi2 = new PublicTaxi(2);

        System.out.println("\n어데 가십니겨?");
        taxi1.PassengerBoarding(2,"서울역",2);

        taxi1.Oiling(-80);
        taxi1.FinalTaxiFare();

        taxi1.PassengerBoarding(5, "구로디지털단지역", 12);
        taxi1.PassengerBoarding(3, "구로디지털단지역", 12);

        taxi1.Oiling(-20);
    }
}
