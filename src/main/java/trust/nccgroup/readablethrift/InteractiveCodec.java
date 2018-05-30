/*
   Copyright 2018 NCC Group

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package trust.nccgroup.readablethrift;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.thrift.TException;
import org.json.JSONObject;

public class InteractiveCodec {

    public static void main(String[] args) {
    	for (String arg : args) {
        	if (arg.equals("encode")) {
        		new InteractiveCodec().encode();
        	} else if (arg.equals("decode")) {
        		new InteractiveCodec().decode();
        	} else {
        		throw new IllegalArgumentException("Unknown argument " + arg);
        	}
        }
    }

    protected String readStdIn() 
    {
    	InputStreamReader isReader = new InputStreamReader(System.in);
		BufferedReader bufReader = new BufferedReader(isReader);

		StringBuilder stringBuilder = new StringBuilder();

		while(true) {
		    try {
		        String inputStr = null;

		        if ((inputStr = bufReader.readLine()) != null) {
		            stringBuilder.append(inputStr);
		        } else {
		            String concatted = stringBuilder.toString();

		            return concatted;
		        }
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		}
    }

    public void encode() 
    {
    	String input = this.readStdIn();

    	JSONObject inputJson = new JSONObject(input);

        try {
            System.out.println(ThriftCodec.b64encodeJson(inputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decode() 
    {
    	String input = this.readStdIn();

    	try {
            JSONObject result = ThriftCodec.decodeB64String(input);

            System.out.println(result.toString(4));
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
