package radar.model;

import java.time.LocalDateTime;

public class Car {

    private final String plateNumber;
    private final LocalDateTime date;
    private final CarType carType;
    private final int speedKmh;
    private final boolean seatbeltOn;

    public Car(String plateNumber, LocalDateTime date, CarType carType, int speedKmh, boolean seatbeltOn) 
    {
        this.plateNumber = plateNumber;
        this.date = date;
        this.carType = carType;
        this.speedKmh = speedKmh;
        this.seatbeltOn = seatbeltOn;
    }

    public String getPlateNumber() { 
        return plateNumber; 
    }
    public LocalDateTime getDate() { 
        return date; 
    }
    public CarType getCarType() { 
        return carType; 
    }
    public int getSpeedKmh() { 
        return speedKmh; 
    }
    public boolean isSeatbeltOn() { 
        return seatbeltOn; 
    }
}
