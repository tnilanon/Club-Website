package edu.cmu.cs15437.clubwebsite.databeans;

import java.util.Date;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserBean implements Comparable< UserBean > {
	private int userId						= -1;
	private String userName					= null;
	private String emailAddress				= null;
	private String hashedPassword			= "*";
	private int salt						= 0;
	private String firstName				= null;
	private String lastName					= null;
	private String sex						= null;
	private int userGroup					= -1;
	private Date membershipExpirationDate	= null;
	
	public UserBean(int userId) {
		this.userId = userId;
	}
	
	public int getUserId()						{ return userId;					}
	public String getUserName()					{ return userName;					}
	public String getEmailAddress()				{ return emailAddress;				}
	public String getHashedPassword()			{ return hashedPassword;			}
	public int getSalt()						{ return salt;						}
	public String getFirstName()				{ return firstName;					}
	public String getLastName()					{ return lastName;					}
	public String getSex()						{ return sex;						}
	public int getUserGroup()					{ return userGroup;					}
	public Date getMembershipExpirationDate()	{ return membershipExpirationDate;	}
	
	public void setUserName(String s)				{ userName = s;					}
	public void setEmailAddress(String s)			{ emailAddress = s;				}
	public void setFirstName(String s)				{ firstName = s;				}
	public void setLastName(String s)				{ lastName = s;					}
	public void setSex(String s)					{ sex = s;						}
	public void setUserGroup(int i)					{ userGroup = i;				}
	public void setMembershipExpirationDate(Date d)	{ membershipExpirationDate = d;	}
	
	public void setPassword(String s) {
		salt = newSalt();
		hashedPassword = hash(s);
	}
	
	public int compareTo(UserBean other) {
		return emailAddress.compareTo(other.emailAddress);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof UserBean) {
			UserBean other = (UserBean) obj;
			return emailAddress.equals(other.emailAddress);
		}
		return false;
	}
	
	public String toString() {
		return "User( " + userId + ", UserName( " + userName + " ) )";
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

