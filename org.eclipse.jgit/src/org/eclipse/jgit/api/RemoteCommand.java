package org.eclipse.jgit.api;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ConfigConstants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;

/**
 * @author raev
 *
 */
public class RemoteCommand extends GitCommand<Collection<String>> {

	private String subCommand;

	private String remoteName;

	private String sourceUri;

	/**
	 * @param repo
	 */
	protected RemoteCommand(Repository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<String> call() throws GitAPIException {
		checkCallable();

		if (subCommand == null) {
			return repo.getConfig().getSubsections(
					ConfigConstants.CONFIG_KEY_REMOTE);
		} else if ("add".equals(subCommand)) {
			StoredConfig config = repo.getConfig();

			config.setString(ConfigConstants.CONFIG_KEY_REMOTE, remoteName,
					ConfigConstants.CONFIG_KEY_URL, sourceUri);
			config.setString(ConfigConstants.CONFIG_KEY_REMOTE, remoteName,
					"fetch", "+refs/heads/*:refs/remotes/origin/*");

			try {
				config.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * @param subCommand
	 */
	public void setSubCommand(String subCommand) {
		this.subCommand = subCommand;
	}

	/**
	 * @param remoteName
	 */
	public void setRemoteName(String remoteName) {
		this.remoteName = remoteName;
	}

	/**
	 * @param sourceUri
	 */
	public void setSourceUri(String sourceUri) {
		this.sourceUri = sourceUri;
	}

}
