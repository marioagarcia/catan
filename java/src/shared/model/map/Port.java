package shared.model.map;

import java.io.Serializable;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.serialization.interfaces.SerializerPortInterface;

@SuppressWarnings("serial")
public class Port implements SerializerPortInterface, Serializable {

	private PortType resource; // If omitted, then it's for any resource, Optional
	private EdgeLocation location;
	private int ratio;
	
	public Port(PortType resource, EdgeLocation location, int ratio){
		this.resource = resource;
		this.location = location;
		this.ratio = ratio;
	}

	public PortType getResource() {
		return resource;
	}

	public void setResource(PortType resource) {
		this.resource = resource;
	}

	public EdgeLocation getLocation() {
		return this.location;
	}

	public void setLocation(EdgeLocation location) {
		this.location = location;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	@Override
	public void setPort(int ratio, PortType resource, EdgeLocation direction) {
		
		this.ratio = ratio;
		this.resource = resource;
		this.location = direction;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ratio;
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Port [resource=" + resource + ", location=" + location
				+ ", ratio=" + ratio + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Port other = (Port) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (ratio != other.ratio)
			return false;
		if (resource != other.resource)
			return false;
		return true;
	}
	
}
