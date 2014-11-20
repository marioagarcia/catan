package shared.model.map;

import java.io.Serializable;

import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.serialization.interfaces.SerializerHexInterface;

@SuppressWarnings("serial")
public class Hex implements HexInterface, SerializerHexInterface, Serializable {
	
	private HexLocation location;
	private HexType resource;
	private int number;

	
	public Hex(HexLocation location, HexType type, int number){
		
		this.location = location;
		this.resource = type;
		this.number = number;
	}

	@Override
	public HexLocation getLocation() {
		return this.location;
	}

	@Override
	public HexType getType() {
		return this.resource;
	}

	@Override
	public void setType(HexType type) {
		this.resource = type;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void setHex(HexLocation hexLocation, HexType resource, int number) {
		this.location = hexLocation;
		this.resource = resource;
		this.number = number;
		
	}

	@Override
	public String toString() {
		return "Hex [location=" + location + ", resource=" + resource
				+ ", number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + number;
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hex other = (Hex) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (number != other.number)
			return false;
		if (resource != other.resource)
			return false;
		return true;
	}

}
