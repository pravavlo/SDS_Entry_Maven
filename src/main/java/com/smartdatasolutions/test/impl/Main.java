package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		// TODO
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {
		return membersFromFile.stream().distinct().collect(Collectors.toList());
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		Map<String, List<Member>> membersByState = new HashMap<>();
		for (Member member : validMembers) {
			String state = member.getState();
			if (!membersByState.containsKey(state)) {
				membersByState.put(state, new ArrayList<>());
			}
			membersByState.get(state).add(member);
		}
		return membersByState;
	}

	public static void main( String[] args )  {
		Main main = new Main();
		MemberImporterImpl fetchMember = new MemberImporterImpl();
		List<Member> members;
		try {
			members = fetchMember.importMembers(new File("Members.txt"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		List<Member> nonDuplicateMembers = main.getNonDuplicateMembers(members);
		Map<String, List<Member>> membersByState = main.splitMembersByState(nonDuplicateMembers);
		for (Map.Entry<String, List<Member>> entry : membersByState.entrySet()) {
			String state = entry.getKey();
			List<Member> membersInState = entry.getValue();
			File file = new File(state + "_outputFile.csv");
			try (Writer writer = new FileWriter(file)) {
				for (Member member : membersInState) {
//					main.getMemberExporter().writeMember(member,writer);
					writer.write(member.toCSVString() + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
