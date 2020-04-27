package com.uxpsystems.assignment.pojo;

public class ProfileEvent {
	private ActionEnum action;
	private Profile profile;
	
	public ProfileEvent() {
		super();
	}
	
	public ProfileEvent(ActionEnum action, Profile profile) {
		super();
		this.action = action;
		this.profile = profile;
	}

	public ActionEnum getAction() {
		return action;
	}
	public void setAction(ActionEnum action) {
		this.action = action;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		ProfileEvent other = (ProfileEvent) obj;
		if (action != other.action)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
	
}
