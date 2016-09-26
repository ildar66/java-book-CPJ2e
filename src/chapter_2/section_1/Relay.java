package chapter_2.section_1;

//Instances of many utility classes used in concurrent settings are intrinsically immutable and are shared by many other objects
class Relay {

    protected final Server server;

    Relay(Server s) {
        server = s;
    }

    void doIt() {
        server.doIt();
    }
}
