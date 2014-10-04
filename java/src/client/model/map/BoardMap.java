package client.model.map;

import java.util.HashMap;
import java.util.Map;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class BoardMap implements BoardMapInterface {
	Map<HexLocation, HexInterface> hexes;
	
	public BoardMap(){
		this.hexes = new HashMap<HexLocation, HexInterface>();
	}

	@Override
	public HexInterface getHex(HexLocation location) throws HexNotFoundException {
		if(!this.hexes.containsKey(location))
			throw new HexNotFoundException();
		
		return this.hexes.get(location);
	}

	@Override
	public HexCornerInterface getVertex(VertexLocation location) {
		return this.hexes.get(location.getHexLoc()).getCorner(location.getDir());
	}

	@Override
	public HexBorderInterface getHexBorder(EdgeLocation location) {
		return this.hexes.get(location.getHexLoc()).getBorder(location.getDir());
	}


}
