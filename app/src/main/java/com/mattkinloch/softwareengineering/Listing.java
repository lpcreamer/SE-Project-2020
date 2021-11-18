package com.mattkinloch.softwareengineering;

import android.util.Log;


/**
 * A request created by an owner-user on the web app to have their pet sat, that can be seen and
 * booked by sitter-users.
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class Listing {

    /**
     * The ID of a listing in the listings database.
     */
    private String id;
    /**
     * The start date of the listing.
     */
    private String date;
    /**
     * The starting time of a listing.
     */
    private String startTime;
    /**
     * The duration of a listing.
     */
    private String duration;
    /**
     * The name of the owner who created the listing.
     */
    private String ownerName;
    /**
     * The name of the pet in the listing.
     */
    private String petName;
    /**
     * The type of pet in the listing.
     */
    private String petType;
    /**
     * The breed of the pet in the listing.
     */
    private String petBreed;
    /**
     * The weight of the pet in the listing.
     */
    private String petWeight;
    /**
     * A description of the pet in the listing.
     */
    private String description;
    /**
     * Additional instructions for pet care for the listing.
     */
    private String petCareInstructions;
    /**
     * The location of the listing.
     */
    private Location location;
    /**
     * The username of the sitter who accepts the listing once it has been booked.
     */
    private String sitterUsername;
    /**
     * Whether or not the listing has been accepted.
     */
    private int booked;

    /**
     * Default constructor for a Listing object
     * @param id The ID for the listing object in the database.
     * @param date The date of this listing object.
     * @param startTime The start time of this listing object.
     * @param duration The duration of this listing object.
     * @param ownerName The name of the owner for this listing object.
     * @param petName The name of the pet to be sat for this listing object.
     * @param petType The type of pet to be sat for this listing object
     * @param petBreed The breed of pet to be sat for this listing object.
     * @param petWeight The weight of the pet to be sat for this listing object.
     * @param petCareInstructions Instructions for the care of the pet in this listing object.
     * @param description Description of this listing in this listing object.
     * @param location Location of this listing in this listing object.
     * @param sitterUsername Username of the sitter of this listing object once booked.
     * @param booked Booking flag for this listing object.
     */
    public Listing(String id, String date, String startTime, String duration, String ownerName,
                   String petName, String petType, String petBreed, String petWeight,
                   String petCareInstructions, String description, Location location, String sitterUsername,
                   int booked) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.ownerName = ownerName;
        this.petName = petName;
        this.petType = petType;
        this.petBreed = petBreed;
        this.petWeight = petWeight;
        this.petCareInstructions = petCareInstructions;
        this.description = description;
        this.location = location;
        this.sitterUsername = sitterUsername;
        this.booked = booked;
    }

    public String getId() { return id; }

    public String getDate() {
        return date;
    }

    public String getStartTime() { return startTime; }

    public String getDuration() {
        return duration;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetType() { return petType; }

    public String getPetBreed() {
        return petBreed;
    }

    public String getPetWeight() { return petWeight; }

    public String getDescription() {
        return description;
    }

    public String getPetCareInstructions() { return petCareInstructions; }

    public Location getLocation() {
        return location;
    }

    public String getSitterUsername() { return sitterUsername; }

    public int getBooked() {
        return booked;
    }

    /**
     * Builds a string representation of a listing object.
     * @return A listing object as a string.
     */
    public String toString() {
        return id + " " + date + " " + " " + startTime + " " + duration + " " + ownerName + " " + petName + " " +
                petBreed + " " + petType + " " + petWeight + " " + description + " " +
                petCareInstructions + " " + location.getZipCode() + " " + booked;
    }
}
