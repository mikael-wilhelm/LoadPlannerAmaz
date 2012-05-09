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

    public void setId(int id) {
        this.id = id;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setHarbor(String harbor) {
        this.harbor = harbor;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Load)) {return false;}

        Load load = (Load) o;

        if (id != load.id) {return false; }

        return true;
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
