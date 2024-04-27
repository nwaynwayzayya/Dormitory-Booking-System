interface Accommodation {
    void bookRoom(Room room) throws RoomNotAvailableException;
    void releaseRoom();
}