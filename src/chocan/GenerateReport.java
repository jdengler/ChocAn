/*
 * Author: Conrad Spear
 * Generates member and provider reports
 */

package chocan;

import static chocan.Terminal.ReadMember;
import static chocan.Terminal.ReadProviders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GenerateReport {

	/**
	 * generateMember() generates a member report for all members and calls print member report
	 * @param none
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */	  
	public static void generateMember() throws FileNotFoundException, IOException{
		ArrayList<MemberAccounts> members = ReadMember();

		for (MemberAccounts ma : members) {
			printMemberReport(ma, ma.getName());
		}		
        JOptionPane.showMessageDialog(null, "All member reports successfully generated and stored in writtenFiles/MemberReports");
		return;
	}
	
	/**
	 * generateIndividualMember() generates a member report base on a member number and calls print member report
	 * @param number, which is the member number of the individual that you want to generate a report for
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void generateIndividualMember(int number) throws FileNotFoundException, IOException{
		ArrayList<MemberAccounts> members = ReadMember();

		for (MemberAccounts ma : members) {
			if (ma.getNumber() == number) {
				printMemberReport(ma, ma.getName());
		        JOptionPane.showMessageDialog(null, "The member report was successfully generated and stored in " + ma.getName().replaceAll("\\s+", "") + ".txt");
				return;
			}

		}

        JOptionPane.showMessageDialog(null, "The member (Number: " + number + ") does not exist");

		return;
	}
	
	/**
	 * generateProvider() generates a member report base on a member number and calls print member report
	 * @param number, which is the member number of the individual that you want to generate a report for
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void generateProvider() throws FileNotFoundException, IOException{
		ArrayList<ProviderAccounts> providers = ReadProviders();

		for (ProviderAccounts pa : providers) {
			printProviderReport(pa, pa.getName());
		}
		
        JOptionPane.showMessageDialog(null, "All provider reports successfully generated and stored in writtenFiles/ProviderReports");

		return;
	}

	/**
	 * generateIndividualProvider() generates a provider report base on a provider number and calls print provider report
	 * @param number, which is the provider number of the individual that you want to generate a report for
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void generateIndividualProvider(int number) throws FileNotFoundException, IOException{
		ArrayList<ProviderAccounts> providers = ReadProviders();

		for (ProviderAccounts pa : providers) {
			if (pa.getNumber() == number) {
				printProviderReport(pa, pa.getName());
		        JOptionPane.showMessageDialog(null, "The provider report was successfully generated and stored in " + pa.getName().replaceAll("\\s+", "") + ".txt");
				return;
			}
		}

        JOptionPane.showMessageDialog(null, "The provider (Number: " + number + ") does not exist");

		return;
	}

	/**
	 * printSummaryReport() generates a summary report for total number of providers, total number of consultations, total number of fees
	 * @param name, which is used to name the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void printSummaryReport(String name) throws FileNotFoundException,IOException{
		ArrayList<ProviderAccounts> providers = ReadProviders();

		String filename = "writtenFiles/ServiceRecords/"+name+".txt";
		double totalFee = 0;
		int totalConsults = 0;

		File file = new File(filename);
	      
		try(BufferedWriter input = new BufferedWriter(new FileWriter(file))){
			for (ProviderAccounts pa : providers) {
				ArrayList<ServiceRecord> sr = pa.getServicesProvided();
				input.write(pa.getName()+",");
				if(sr == null) {
					input.write("0"+","+"0\n");
				}
				else {
					input.write(pa.getNumberServices()+",");
					input.write(pa.getTotalFees(sr)+"\n");
					totalConsults = totalConsults + pa.getNumberServices();
					totalFee = totalFee + pa.getTotalFees(sr);
				}
			}
			input.write("Total number of providers: "+providers.size()+"\n");
			input.write("Total number of consultations: "+totalConsults+"\n");
			input.write("Total number of fees: $"+totalFee+"\n");
			input.close();
		}
        JOptionPane.showMessageDialog(null, "The summary report was successfully generated and stored in "+name+".txt");
		return;
	}

	
	/**
	 * printMemberReport() prints the member report (used by Manager)
	 * @param member, which is the member object to be reported on
	 * @param name, which is used to name the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void printMemberReport(MemberAccounts member, String name) throws FileNotFoundException, IOException{
		name = name.replaceAll("\\s+", "");
		String filename = "writtenFiles/MemberReports/"+name+".txt";

		File file = new File(filename);
	      
		try(BufferedWriter input = new BufferedWriter(new FileWriter(file))){
			input.write(member.getName()+"\n"+member.getNumber()+"\n"+member.getAddress()+"\n"+member.getCity()+"\n"+member.getState()+"\n"+member.getZipCode()+"\n");

			ArrayList<ServiceRecord> sr = member.getServicesProvided();
			if (sr != null) {
				for(ServiceRecord service : sr) {
					input.write(service.getDateOfService()+"\n"+service.getProviderName(service.getProviderNumber())+"\n"+service.getServiceName(service.getServiceCode())+"\n");
				}
			}

			input.close();
		}

	}


	/**
	 * printProviderReport() prints the provider report (used by Manager)
	 * @param provider, which is the provider object to be reported on
	 * @param name, which is used to name the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void printProviderReport(ProviderAccounts provider, String name) throws FileNotFoundException, IOException{
		name = name.replaceAll("\\s+", "");
		double totalFee = 0;
		String filename = "writtenFiles/ProviderReports/"+name+".txt";

		File file = new File(filename);
	      
		try(BufferedWriter input = new BufferedWriter(new FileWriter(file))){
			input.write(provider.getName()+"\n"+provider.getNumber()+"\n"+provider.getAddress()+"\n"+provider.getCity()+"\n"+provider.getState()+"\n"+provider.getZipCode()+"\n");

			ArrayList<ServiceRecord> sr = provider.getServicesProvided();
			if (sr != null) {
				for(ServiceRecord service : sr) {
					input.write(service.getDateOfService()+"\n"+service.getCurrentDateTime()+"\n"+service.getMemberName(service.getMemberNumber())+"\n"+service.getMemberNumber()+"\n"+service.getServiceCode()+"\n"+service.getServiceFee(service.getServiceCode())+"\n");
					totalFee = totalFee + service.getServiceFee(service.getServiceCode());
				}
				input.write("Total number of consultations with members: "+sr.size()+"\n");
				input.write("Total fees for the week: $"+totalFee+"\n");
			}
			else {
				input.write("Total number of consultations with members: 0\n");
				input.write("Total fees for the week: $0\n");
			}
			input.close();
		}
	}
}
