package swd20.levykokoelma.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Levy {
	int kuluvaVuosi=LocalDate.now().getYear();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long levy_id;
    @NotBlank(message = "Nimi on pakollinen tieto.")
	private String nimi;
	@ManyToOne
	// @JsonIgnoreProperties - one way to avoid infinite loop during JSON serialization/deserialization with bidirectional relationships
    @JsonIgnoreProperties ("levyt") 
	@JoinColumn(name="artisti_id")
	private Artisti artisti;
	@NotNull
    @Size(min = 0, max = 200, message = "Infon maksimipituus 200 merkkiä")
	private String info;
	private int kesto;
	@Min(value = 1900, message = "Vuoden pitää olla välillä 1900 - 2021")
    @Max(value = 2021, message = "Vuoden pitää olla välillä 1900 - 2021")
	private int vuosi;
	@ManyToOne
	// @JsonIgnoreProperties - one way to avoid infinite loop during JSON serialization/deserialization with bidirectional relationships
    @JsonIgnoreProperties ("levyt") 
	@JoinColumn(name="formaatti_id")
	private Formaatti formaatti;
	
	public Levy() {
		super();
	}
	
	public Levy(String nimi,Artisti artisti) {
		super();
		this.nimi=nimi;
		this.artisti = artisti;
	}

	public Levy(String nimi, Artisti artisti, String info, int kesto, int vuosi, Formaatti formaatti) {
		super();
		this.nimi=nimi;
		this.artisti = artisti;
		this.info = info;
		this.kesto = kesto;
		this.vuosi = vuosi;
		this.formaatti = formaatti;
	}


	@Override
	public String toString() {
		return "Levy [levy_id=" + levy_id + ", nimi=" + nimi + ", artisti=" + artisti + ", info=" + info + ", kesto="
				+ kesto + ", vuosi=" + vuosi + ", formaatti=" + formaatti + "]";
	}

	public long getLevy_id() {
		return levy_id;
	}

	public void setLevy_id(long levy_id) {
		this.levy_id = levy_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getKesto() {
		return kesto;
	}

	public void setKesto(int kesto) {
		this.kesto = kesto;
	}

	public int getVuosi() {
		return vuosi;
	}

	public void setVuosi(int vuosi) {
		this.vuosi = vuosi;
	}

	public Artisti getArtisti() {
		return artisti;
	}

	public void setArtisti(Artisti artisti) {
		this.artisti = artisti;
	}

	public Formaatti getFormaatti() {
		return formaatti;
	}

	public void setFormaatti(Formaatti formaatti) {
		this.formaatti = formaatti;
	}
	
	
	
	
	
	
}
