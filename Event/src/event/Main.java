package event;

import java.util.Scanner;

public class Main {
	static dbconnection connection=new dbconnection();
	static int id;
	public static void main(String[] args) {
		Scanner cin=new Scanner(System.in);
		System.out.println(" ----------------------------------");
		System.out.println("|      Event Management system     |");
		System.out.println(" ----------------------------------");
		int choice;
		do {

			System.out.println();
			System.out.println("1:Admin\t2:Customer\t3:Staff");
			System.out.println("Enter your choice:");
			choice=cin.nextInt();
			switch (choice) {
			case 1:{
				Admin ad=new Admin();
				boolean b=ad.login();
				if(b) {
					ad.manage();

				}else {
					System.out.println("You are not Admin");
				}

			}

			break;

			case 2:{
				Customer c=new Customer();
				int ch;		

				do {
					System.out.println("1:Register\t2:Login");
					System.out.println("Enter your choice:");
					ch=cin.nextInt();
					switch (ch) {
					case 1:
					{
						c.register();
					}
					break;
					case 2:
					{
						boolean b=c.login();
						if(b) {
							int ch1;
							do {System.out.println("1:View-Events\t2:Book-events\t3:cancel-booking\t4:view-booked-event\t5:view-booked-status\t0:Exit");
							ch1=cin.nextInt();
							switch (ch1) {
							case 1:{
								Admin ad=new Admin();
								ad.viewEvents();
							}
							break;
							case 2:{
								Admin ad=new Admin();
								ad.viewEvents();
								Booking book=new Booking();
								int eid;
								System.out.println("Book your event:");
								System.out.println("Enter the id to book the events:");
								eid=cin.nextInt();
								book.booking(id,eid);

							}
							break;

							case 3:{
								Admin ad=new Admin();
								ad.viewEvents();
								Booking book1=new Booking();
								int eid;
								System.out.println("Cancel your event:");
								System.out.println("Enter the id to cancel the events:");
								eid=cin.nextInt();
								book1.cancelEvents(id,eid);

							}
							break;
							case 4:{
								Admin ad=new Admin();
								ad.bookedevents(id);

							}
							break;
							case 5:
								Customer customer=new Customer();
								customer.bookedsts();
								break;

							default:
								break;
							}
							} while (ch1!=0);
						}else {
							System.out.println("User not found");
						}
					}
					break;

					default:
						break;
					}
				}while(ch!=0);



			}
			break;

			default:
				break;
			}
		}while(choice!=0);

	}

}
