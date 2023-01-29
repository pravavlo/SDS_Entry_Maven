package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List< Member > importMembers( File inputFile ) throws Exception {
		 inputFile = new File("Members.txt");
		 List<Member> input = new ArrayList<>();

		/*
		 * Implement the missing logic
		 */

		try (BufferedReader br = new BufferedReader( new FileReader( inputFile ) )) {
			String line = br.readLine( );

			while ( line != null ) {
				Member member = new Member();
				member.setId(line.substring(0,12).trim());
				member.setLastName(line.substring(12, 37).trim());
				member.setFirstName(line.substring(37, 62).trim());
				member.setAddress(line.substring(62, 92).trim());
				member.setCity(line.substring(92, 112).trim());
				member.setState(line.substring(112, 116).trim());
				member.setZip(line.substring(116, 121).trim());
				input.add(member);
				line = br.readLine( );
			}
		}
		return input;
	}
}
