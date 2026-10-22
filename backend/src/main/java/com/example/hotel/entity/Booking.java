package com.example.hotel.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "check in date is required ")
    private LocalDate checkInDate;
    @Future(message = "check out date must be in the future ")
    private LocalDate checkOutDate;

    @Min(value = 1,message = "Number of adults must not be less than 1")
    private int numOfAdults;
    @Min(value = 0,message = "Number of children must not be less than 0")
    private int numOfChildren;
    private int totalNumofGuest;
    private String bookingConformationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_Id")
    private Room room;

    public void calculateTotalnumberOfGuest(){
        this.totalNumofGuest=this.numOfChildren+this.numOfAdults;
    }

    public void setNumberOfAdult(int numOfAdults){
        this.numOfAdults=numOfAdults;
        calculateTotalnumberOfGuest();
    }

    public void setNumberOfChilder(int numOfChildren){
        this.numOfChildren=numOfChildren;
        calculateTotalnumberOfGuest();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numOfAdults=" + numOfAdults +
                ", numOfChildren=" + numOfChildren +
                ", totalNumOfGuest=" + totalNumofGuest +
                ", bookingConfirmationCode='" + bookingConformationCode + '\'' +
                '}';
    }
}
