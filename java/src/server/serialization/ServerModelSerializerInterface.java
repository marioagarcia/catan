package server.serialization;

import shared.serialization.parameters.CredentialsParameters;

public interface ServerModelSerializerInterface {
	
	public CredentialsParameters deserializeCredentials(String jsonString)

}
