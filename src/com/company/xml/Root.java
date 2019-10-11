package com.company.xml;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Vector;

@XmlRootElement(name = "Root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlElementWrapper(name = "artistList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "artist")
    private List<Artist> artists = new Vector<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}