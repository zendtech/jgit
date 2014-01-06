package org.eclipse.jgit.pgm;

import java.util.Collection;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RemoteCommand;
import org.kohsuke.args4j.Argument;

/**
 * @author raev
 *
 */
public class Remote extends TextBuiltin {

	@Argument(index = 0, metaVar = "metaVar_command")
	private String subCommand;

	@Argument(index = 1, metaVar = "metaVar_remoteName")
	private String remoteName;

	@Argument(index = 2, metaVar = "metaVar_uriish")
	private String sourceUri;

	@Override
	protected void run() throws Exception {
		RemoteCommand cmd = new Git(db).remote();
		cmd.setSubCommand(subCommand);
		cmd.setRemoteName(remoteName);
		cmd.setSourceUri(sourceUri);

		Collection<String> result = cmd.call();

		if (result != null) {
			for (String line : result) {
				System.out.println(line);
			}
		}
	}

}
