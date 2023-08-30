public class BackUpFacade {
    private AddressBookManagement addressBookManagement;
    private NoteManagement noteManagement;
    private PhotoManagement photoManagement;
    private MusicManagement musicManagement;

    public BackUpFacade(){
        addressBookManagement = new AddressBookManagement();
        noteManagement = new NoteManagement();
        photoManagement = new PhotoManagement();
        musicManagement = new MusicManagement();
    }

    public void copy(){
        addressBookManagement.copy();
        noteManagement.copy();
        photoManagement.copy();
        musicManagement.copy();
    }
}
