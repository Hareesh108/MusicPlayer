package com.harsh;

import java.util.*;

public class MusicPlayer {

	private static ArrayList<Album> albums = new ArrayList<>();

	public static void main(String[] args) {

		Album album = new Album("English", "Zyan");

		album.addSong("Trampoline", 3.04);
		album.addSong("PillowTalk", 3.5);
		album.addSong("Dusk till down", 4.0);
		albums.add(album);

		album = new Album("Hindi", "Arijit Singh");

		album.addSong("Shayad", 4.5);
		album.addSong("Kesariyaa", 3.5);
		album.addSong("Apana Bana Le", 4.5);

		albums.add(album);

		album = new Album("Marathi", "Ajay Atul");

		album.addSong("Ved Lavlay", 3.5);
		album.addSong("Sapana Jahan", 6.5);
		album.addSong("Chikani Chameli", 4);

		albums.add(album);

		LinkedList<Song> songList = new LinkedList<>();

		albums.get(0).addToPlayList("Trampoline", songList);
		albums.get(0).addToPlayList("PillowTalk", songList);
		albums.get(0).addToPlayList("Dusk till down", songList);

		albums.get(1).addToPlayList("Shayad", songList);
		albums.get(1).addToPlayList("Kesariyaa", songList);
		albums.get(1).addToPlayList("Apana Bana Le", songList);

		albums.get(2).addToPlayList("Ved Lavlay", songList);
		albums.get(2).addToPlayList("Sapana Jahan", songList);
		albums.get(2).addToPlayList("Chikani Chameli", songList);

		play(songList);

	}

	private static void play(LinkedList<Song> playList) {
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		boolean forward = true;
		boolean backward = true;
		ListIterator<Song> listIterator = playList.listIterator();

		if (playList.size() == 0) {
			System.out.println("PlayList is Empty");
		} else {
			System.out.println();
			System.out.println("Now playing -> " + listIterator.next().toString());
			System.out.println();
			printMenu();
		}

		while (!quit) {
			int action = sc.nextInt();
			sc.nextLine();

			switch (action) {

			case 1:
				while (backward) {
					printList(playList);

					System.out.println("1. Play song using number \n" + "2. Back()");

					action = sc.nextInt();
					switch (action) {
					case 1: {
						System.out.println("Enter a song number");
						action = sc.nextInt();
						Iterator<Song> iterator = playList.iterator();
						int num = 0;
						while (iterator.hasNext()) {
							num++;
							if (action == num) {
								System.out.println(iterator.next());
							}
							iterator.next();
						}

						System.out.println();
						break;
					}
					case 2: {
						printMenu();
						backward = false;
						break;
					}
					}
				}
				break;

			case 2:
				if (forward) {
					if (listIterator.hasPrevious()) {
						listIterator.previous();
					}
					forward = false;
				}
				if (listIterator.hasPrevious()) {
					System.out.println("Now playing " + listIterator.previous().toString());
				} else {
					System.out.println("This is first song");
					forward = false;
				}
				break;

			case 3:
				if (forward) {
					if (listIterator.hasPrevious()) {
						System.out.println("Now playing " + listIterator.previous().toString());
						forward = false;
					} else {
						System.out.println("we are at the start of the list");
					}
				} else {
					if (listIterator.hasNext()) {
						System.out.println("now playing " + listIterator.next().toString());
						forward = true;
					} else {
						System.out.println("we have reached to the end of list");
					}
				}
				break;

			case 4:
				if (!forward) {
					if (listIterator.hasNext()) {
						listIterator.next();
					}
					forward = true;
				}
				if (listIterator.hasNext()) {
					System.out.println("Now playing " + listIterator.next().toString());
				} else {
					System.out.println("no song availble, reached to the end of the list");
					forward = false;
				}
				break;

			case 5:
				if (playList.size() > 0) {
					listIterator.remove();
					if (listIterator.hasNext()) {
						System.out.println("now playing " + listIterator.next().toString());
					} else {
						if (listIterator.hasPrevious())
							System.out.println("now playing " + listIterator.previous().toString());
					}
				}
				break;

			case 6:
				System.out.println("Thank you!!");
				quit = true;
				break;

			}
		}
	}

	private static void printMenu() {
		System.out.println(" <----- MENU ----->");
		System.out.println("1 - All List\n" + "2 - to Previous Song \n" + "3 - to Restart\n" + "4 - to Play Next \n"
				+ "5 - to Delete Current Song\n" + "6 - to Stop()\n");
	}

	private static void printList(LinkedList<Song> playList) {
		Iterator<Song> iterator = playList.iterator();
		System.out.println();
		int num = 1;
		while (iterator.hasNext()) {
			System.out.print(num++ + " ");
			System.out.println(iterator.next());
		}

		System.out.println();
	}

}