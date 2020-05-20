package demos.web.socket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatServer {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();
    
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        String user = session.getUserPrincipal().getName();
        session.getAsyncRemote().sendText("Welcome "+user+"!");
        broadcastMessage(user+" has joined the chat");
    }
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        sessions.remove(session);
        String user = session.getUserPrincipal().getName();
        broadcastMessage(user+" has left the chat");
    }
    @OnError
    public void onError(Session session, Throwable ex) {
        logger.log(Level.INFO, "Web Socket Error",ex);
        session.getAsyncRemote().sendText(ex.getMessage()); 
    }
    @OnMessage
    public void onMessage(Session session, String message) {
        if(message == null || message.length() == 0){
            throw new RuntimeException("No actual message received");
        }
        String user = session.getUserPrincipal().getName();
        broadcastMessage(user+": "+message);
    }
    private void broadcastMessage(String message) {
        sessions.stream().forEach(s -> s.getAsyncRemote().sendText(message));
    }
    
}
