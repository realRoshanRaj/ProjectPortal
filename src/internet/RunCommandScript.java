package internet;

import java.io.IOException;

public class RunCommandScript {

	public RunCommandScript(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

}
