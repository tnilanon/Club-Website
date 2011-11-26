package edu.cmu.cs15437.clubwebsite.databeans;

import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Comparable< User > {
	private String emailAddress		= null;
	private String hashedPassword	= "*";
	private int salt				= 0;
	private String firstName		= null;
	private String lastName			= null;
	
	public User(String emailAddress) {
		this.emailAddress	= emailAddress;
	}
	
	public String getEmailAddress()		{ return emailAddress;		}
	public String getHashedPassword()	{ return hashedPassword;	}
	public int getSalt()				{ return salt;				}
	public String getFirstName()		{ return firstName;			}
	public String getLastName()			{ return lastName;			}
	
	public void setHashedPassword(String s) { hashedPassword = s;	}
	public void setSalt(int x)				{ salt = x;				}
	public void setFirstName(String s)		{ firstName = s;		}
	public void setLastName(String s)		{ lastName = s;			}
	
	public void setPassword(String s)		{ salt = newSalt(); hashedPassword = hash(s); }
	
	public int compareTo(User other) {
		int c;
		// Order by lastName first, by firstName next, then by emailAddress
		c = lastName.compareTo(other.lastName);
		if (c != 0) return c;
		c = firstName.compareTo(other.firstName);
		if (c != 0) return c;
		return emailAddress.compareTo(other.emailAddress);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			return emailAddress.equals(other.emailAddress);
		}
		return false;
	}
	
	public String toString() {
		return "User(" + emailAddress + ")";
	}
	
	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	private int newSalt() {
		Random r = new Random();
		return r.nextInt(8192) + 1; // Salt cannot be zero
	}
	
	private String hash(String clearPassword) {
		if (salt == 0) return null;
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError("Cannot find SHA1 algorithm in java.security package");
		}
		
		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();
		
		StringBuffer digestSB = new StringBuffer();
		for (int i = 0; i < digestBytes.length; ++i) {
			int lowNibble = digestBytes[i] & 0x0F;
			int highNibble = (digestBytes[i] >> 4) & 0x0F;
			digestSB.append(Integer.toHexString(highNibble));
			digestSB.append(Integer.toHexString(lowNibble));
		}
		
		return digestSB.toString();
	}
}

