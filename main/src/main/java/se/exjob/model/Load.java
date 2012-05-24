package se.exjob.model;

public class Load {
    private String content;
    private String harbor;
    private int id;
    private boolean reserved = false;
    private String destination;
    private User reservedByUser;

    public Load( int id, String content, String harbor, String destination) {
        this.content = content;
        this.harbor = harbor;
        this.id = id;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public boolean getReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getContent() {
        return content;
    }

    public String getHarbor() {
        return harbor;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Load)) {return false;}

        Load load = (Load) o;

        return id == load.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void setReservedBy(User user) {
        reservedByUser = user;
    }

    public User getReservedBy(){
        return reservedByUser;
    }
}
