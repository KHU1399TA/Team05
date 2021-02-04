package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static final String USERS_FILE_PATH = "src/resources/Usernames-and-Passwords";
	private static final String FOODS_FILE_PATH = "src/resources/Food";
	
	public static ArrayList<Order> currentOrders = new ArrayList<>();
	public static String username_for_order = "";
	public static int id_of_order = 1000;
	public static boolean repeatFlag = true;
	
	public static String currentTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return myDateObj.format(myFormatObj);
	}
	
	public static AccessLevel login() {
		
		String result = "not_ok";
		String accessLevel = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username and password");
		String username = scanner.nextLine();
		String password = scanner.nextLine();
		StringBuilder allString = new StringBuilder();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"src/resources/Usernames-and-Passwords"));
			String line = reader.readLine();
			
			while (line != null) {
				
				
				String ins1 = line;
				String[] ins2 = ins1.split(",");
				String username2 = ins2[3];
				String password2 = ins2[4];
				
				if (username.equals(username2) && password.equals(password2)) {
					
					username_for_order = username2;
					
					result = "ok";
					accessLevel = ins2[5];
					
					ins2[7] = currentTime();
					StringBuilder ins3 = new StringBuilder();
					for (int i = 0; i < 8; i++) {
						if (i != 7) {
							ins3.append(ins2[i]).append(",");
						} else {
							ins3.append(ins2[i]);
						}
					}
					
					allString.append(ins3);
				} else {
					allString.append(line);
				}
				allString.append("\n");
				
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileManager fileManager = new FileManager(USERS_FILE_PATH);
		fileManager.write(allString.toString(), false);
		
		if (result.equals("ok")) {
			System.out.println("Login is successful :)");
			
			switch (accessLevel) {
				case "MANAGER":
					return AccessLevel.MANAGER;
				//break;
				case "CASHIER":
					return AccessLevel.CASHIER;
				//break;
				case "CHEF":
					return AccessLevel.CHEF;
				//break;
				case "DELIVERYMAN":
					return AccessLevel.DELIVERYMAN;
				//break;
				case "CLIENT":
					return AccessLevel.CLIENT;
				//break;
			}
		} else {
			System.out.println("Login is not successful due to invalid username or password :(.");
		}
		return AccessLevel.NOACCESSLEVEL;
	}
	
	public static void main(String[] args) {
		
		
		while (repeatFlag) {
			
			
			System.out.println();
			
			
			Scanner scanner = new Scanner(System.in);
			
			
			System.out.println("Please enter 1 if you want to sign up a client, else enter 2!");
			
			String reply = scanner.nextLine();
			if (reply.equals("1")) {
				
				Manager mng = new Manager();
				
				User usr = new User() {
				};
				scanUserInfo(scanner, usr);
				
				System.out.println("Please enter accessLevel! (CLIENT)");
				String ans = scanner.nextLine();
				
				
				usr.registrationDate = currentTime();
				usr.lastLoginDate = currentTime();
				
				
				if (ans.equals("CLIENT")) {
					usr.accessLevel = AccessLevel.CLIENT;
					if (mng.register(usr) == ActionResult.SUCCESS) {
						System.out.println();
						System.out.println("User was added successfully");
						System.out.println();
						System.out.println("Now continue with any user!");
						System.out.println();
					} else {
						System.out.println();
						System.out.println("Username already exist and was not added successfully!");
						System.out.println();
						System.out.println("Now continue with any user!");
						System.out.println();
					}
				} else {
					System.out.println();
					System.out.println("Sorry! the accessLevel you entered is not CLIENT");
					System.out.println();
					System.out.println("Now continue with any user!");
					System.out.println();
				}
			}
			
			
			String result_of_login = login().toString();
			
			switch (result_of_login) {
				case "MANAGER" -> {
					boolean managerFlag = true;
					System.out.println("You are a Manager!");
					Manager mng = new Manager();
					User usr = new User() {
					};
					while (managerFlag) {
						
						System.out.println("What do you want to do? (register/edit/remove)");
						
						switch (scanner.nextLine()) {
							case "register" -> {
								scanUserInfo(scanner, usr);
								System.out.println("Please enter accessLevel! (MANAGER/CASHIER/CHEF/DELIVERYMAN/CLIENT)");
								
								switch (scanner.nextLine()) {
									case "MANAGER" -> usr.accessLevel = AccessLevel.MANAGER;
									case "CASHIER" -> usr.accessLevel = AccessLevel.CASHIER;
									case "CHEF" -> usr.accessLevel = AccessLevel.CHEF;
									case "DELIVERYMAN" -> usr.accessLevel = AccessLevel.DELIVERYMAN;
									case "CLIENT" -> usr.accessLevel = AccessLevel.CLIENT;
								}
								usr.registrationDate = currentTime();
								usr.lastLoginDate = currentTime();
								if (mng.register(usr) == ActionResult.SUCCESS) {
									System.out.println("User was added successfully");
								} else {
									System.out.println("Username already exist and was not added successfully. Try again!");
								}
							}
							case "edit" -> {
								boolean repeat = true;
								
								System.out.println("Enter the username to edit!");
								String userToEdit = scanner.nextLine();
								
								while (repeat) {
									System.out.println("Which one do you want to edit for the user " + userToEdit + "?");
									System.out.println(
											"name(enter 0) / familyName (enter 1) / phoneNumber (enter 2) / password (enter 4) / accessLevel (enter 5)");
									int index = scanner.nextInt();
									scanner.nextLine();
									System.out.println("Please Enter your change:");
									String ans3 = scanner.nextLine();
									if (mng.edit(userToEdit, index, ans3).toString().equals("SUCCESS")) {
										System.out.println("Your change is Done!");
										System.out.println("Do you want to edit another field for the user " + userToEdit + "? y/n");
										String ans2 = scanner.nextLine();
										if (ans2.equals("n")) {
											repeat = false;
										}
									}
								}
							}
							case "remove" -> {
								boolean repeat = true;
								
								while (repeat) {
									System.out.println("Enter the username to remove!");
									String user_to_remove = scanner.nextLine();
									
									if (mng.remove(user_to_remove).toString().equals("SUCCESS")) {
										System.out.println("The user removed successfully!");
										System.out.println("Do you want to remove another user? y/n");
										String ans2 = scanner.nextLine();
										if (ans2.equals("n")) {
											repeat = false;
										}
									}
								}
							}
						}
						System.out.println();
						System.out.println("To logout current user please enter 1, else enter 2");
						String r = scanner.nextLine();
						if (r.equals("1")) {
							managerFlag = false;
						}
					}
				}
				case "CHEF" -> {
					System.out.println("You are a Chef!");
					
					System.out.println("What do you want to do? (cook/addFood/removeFood)");
					String ans = scanner.nextLine();
					
					switch (ans) {
						case "addFood": {
							showFoodsList();
							
							Food newFood = new Food();
							
							System.out.println();
							System.out.println("Please enter new and not duplicate food id:");
							int foodID = scanner.nextInt();
							scanner.nextLine();
							newFood.id = foodID;
							
							System.out.println("Please enter new and not duplicate name of food:");
							newFood.name = scanner.nextLine();
							
							System.out.println("Please enter ingredients one by one. Quit with entering \"quit\"");
							
							ArrayList<String> ingredients = new ArrayList<>();
							while (scanner.hasNextLine()) {
								
								String ingredients2 = scanner.nextLine();
								if (ingredients2.equals("quit")) {
									break;
								}
								
								ingredients.add(ingredients2);
							}
							newFood.ingredients = ingredients;
							
							Chef newChef = new Chef();
							if (newChef.addFood(newFood).toString().equals("SUCCESS")) {
								System.out.println();
								System.out.println("The food was added successfully!");
							} else {
								System.out.println();
								System.out.println("Sorry, the food was not added successfully, due to duplicate id or food name!");
							}
							break;
						}
						case "editFood":
							
							showFoodsList();
							break;
						case "removeFood": {
							
							showFoodsList();
							System.out.println("Please enter foodID to remove from list!");
							ans = scanner.nextLine();
							Chef newChef = new Chef();
							if (newChef.removeFood(ans).toString().equals("SUCCESS")) {
								System.out.println("Food removed successfully!");
							} else {
								System.out.println("Food id wasn't found and wasn't removed!");
							}
							break;
						}
						case "changeFoodState":
							
							break;
						case "cook":
							boolean atLeastOneOrderIsConfirmed = false;
							for (Order currentOrder : currentOrders) {
								if (currentOrder.state.equals("CONFIRMED")) {
									atLeastOneOrderIsConfirmed = true;
									break;
								}
							}
							
							if (currentOrders.size() == 0 || !atLeastOneOrderIsConfirmed) {
								System.out.println("Sorry, there is no order to cook!");
							} else {
								System.out.println();
								System.out.println("There are some order(s) to cook!");
								System.out.println();
								System.out.println("This is the list of order(s):");
								
								for (Order currentOrder : currentOrders) {
									try {
										BufferedReader reader = new BufferedReader(new FileReader(FOODS_FILE_PATH));
										String line = reader.readLine();
										
										while (line != null) {
											String ins1 = line;
											String[] ins2 = ins1.split(",");
											String foodName = ins2[1];
											int foodId = Integer.parseInt(ins2[0]);
											
											if (currentOrder.foodId == foodId && currentOrder.state.equals("CONFIRMED")) {
												System.out.println("Order id: " + currentOrder.id + ", Type of food: " + foodName);
											}
											
											line = reader.readLine();
										}
										
										reader.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
							
							System.out.println();
							
							if (atLeastOneOrderIsConfirmed) {
								System.out.println("Do you want to cook the foods? y/n");
								ans = scanner.nextLine();
								if (ans.equals("y")) {
									for (Order currentOrder : currentOrders) {
										Chef newChef = new Chef();
										
										if (currentOrder.state.equals("CONFIRMED")) {
											if (newChef.cook(currentOrder.id).toString().equals("SUCCESS")) {
												System.out.println("Order(s) cooked");
											}
										}
									}
								}
							}
							break;
					}
				}
				case "CASHIER" -> {
					System.out.println("You are a Cashier!");
					
					boolean ifanyorder = false;
					
					for (Order currentOrder : currentOrders) {
						if (currentOrder.state.equals("NOTREADY")) {
							ifanyorder = true;
							break;
						}
					}
					
					if (currentOrders.size() == 0 || !ifanyorder) {
						System.out.println("Sorry, there is no order to confirm!");
					} else {
						
						System.out.println();
						System.out.println("There are some order(s) to confirm!");
						System.out.println();
						System.out.println("This is the list of order(s):");
						
						for (Order currentOrder : currentOrders) {
							if (currentOrder.state.equals("NOTREADY")) {
								
								System.out.println("Order id: " + currentOrder.id + ", Username: " +
										currentOrder.userName);
							}
						}
					}
					
					if (ifanyorder) {
						System.out.println("Do you want to confirm the foods? y/n");
						String ans = scanner.nextLine();
						if (ans.equals("y")) {
							for (Order currentOrder : currentOrders) {
								Cashier newCashier = new Cashier();
								if (currentOrder.state.equals("NOTREADY")) {
									if (newCashier.confirmOrder(currentOrder.id).toString().equals("SUCCESS")) {
										System.out.println("Order(s) confirmed");
									}
								}
							}
						}
					}
				}
				case "DELIVERYMAN" -> {
					System.out.println("You are a Deliveryman!");
					
					boolean ifanyorder = false;
					
					for (Order currentOrder : currentOrders) {
						if (currentOrder.state.equals("COOKED")) {
							ifanyorder = true;
							break;
						}
					}
					if (currentOrders.size() == 0 || !ifanyorder) {
						System.out.println("Sorry, there is no order to deliver!");
					} else {
						System.out.println();
						System.out.println("There are some order(s) to deliver!");
						System.out.println();
						System.out.println("This is the list of order(s):");
						
						for (Order currentOrder : currentOrders) {
							if (currentOrder.state.equals("COOKED")) {
								System.out.println("Order id: " + currentOrder.id + ", Username: " +
										currentOrder.userName + ", Address: " + currentOrder.address);
							}
						}
					}
					
					if (ifanyorder) {
						System.out.println("Do you want to deliver the foods? y/n");
						String ans = scanner.nextLine();
						if (ans.equals("y")) {
							for (Order currentOrder : currentOrders) {
								DeliverMan newDelMan = new DeliverMan();
								if (currentOrder.state.equals("COOKED")) {
									if (newDelMan.deliver(currentOrder.id).toString().equals("SUCCESS")) {
										System.out.println("Order(s) delivered");
									}
								}
							}
						}
					}
				}
				case "CLIENT" -> {
					System.out.println("You are a Client!");
					System.out.println("Please enter 1 if you ordered before, otherwise enter 2 to make a new order!");
					String ans = scanner.nextLine();
					if (ans.equals("1")) {
						
						System.out.println("Enter 1 to check your state of order,or enter 2 to revoke your order!");
						ans = scanner.nextLine();
						if (ans.equals("1")) {
							
							System.out.println("Please enter your order id!");
							int ans2 = scanner.nextInt();
							scanner.nextLine();
							
							if (currentOrders.size() == 0) {
								System.out.println("Sorry! We didn't find such order with your username!");
							}
							
							for (Order currentOrder : currentOrders) {
								if (currentOrder.userName.equals(username_for_order) && currentOrder.id == ans2) {
									System.out.print("Your order state is: ");
									System.out.println(currentOrder.state);
								} else {
									System.out.println("Sorry! We didn't find such order with your username!");
								}
							}
						} else {
							System.out.println("Please enter your order id to revoke!");
							int orderId = scanner.nextInt();
							scanner.nextLine();
							
							Client newClient = new Client();
							ActionResult result = newClient.revokeOrder(orderId);
							
							if (result == ActionResult.ORDER_ALREADY_COOKED) {
								System.out.println("Sorry! Order Already Cooked or Does not Exist and Can not be Revoked");
							} else {
								System.out.println("Thank You, your order revoked!");
							}
						}
					} else if (ans.equals("2")) {
						
						System.out.println("------This is our MENU------");
						showFoodMenu();
						
						Order newOrder = new Order();
						Client newClient = new Client();
						String str = newClient.makeOrder(newOrder).toString();
						String str2 = "ORDER_ALREADY_EXIST";
						if (str.equals(str2)) {
							System.out.println("Sorry! Order Already Exist");
						} else {
							System.out.println("Thank You, We Received Your Order!");
						}
					}
				}
			}
			
			System.out.println();
			System.out.println("------------------------------------Job is Done------------------------------------");
			System.out.println("-----------if you want to exit this program please enter 1, else enter 2-----------");
			
			if (scanner.nextLine().equals("1")) {
				repeatFlag = false;
			}
		}
	}
	
	private static void showFoodMenu() {
		System.out.println();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"src/resources/Food"));
			String line = reader.readLine();
			while (line != null) {
				
				String ins1 = line;
				String[] ins2 = ins1.split(",");
				System.out.print(ins2[0]);
				System.out.print(",");
				System.out.println(ins2[1]);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void showFoodsList() {
		System.out.println();
		System.out.println("------This is the list of foods------");
		showFoodMenu();
	}
	
	private static void scanUserInfo(Scanner scanner, User usr) {
		System.out.println("Please enter FirstName");
		usr.firstName = scanner.nextLine();
		System.out.println("Please enter LastName");
		usr.lastName = scanner.nextLine();
		System.out.println("Please enter PhoneNumber");
		usr.phoneNumber = scanner.nextLine();
		System.out.println("Please enter UserName");
		usr.userName = scanner.nextLine();
		System.out.println("Please enter PassWord");
		usr.password = scanner.nextLine();
	}
}
