package ru.relex.miniBooking.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.relex.miniBooking.commons.model.ChatListModel;
import ru.relex.miniBooking.commons.model.ChatModel;
import ru.relex.miniBooking.rest.util.IdExtractor;
import ru.relex.miniBooking.rest.util.UsernameExtractor;
import ru.relex.miniBooking.services.facade.ChatFacade;
import ru.relex.miniBooking.services.model.chat.Message;

import java.util.List;

@RestController
@RequestMapping ("/chat")
public class ChatController {
    private final UsernameExtractor usernameExtractor;
    private final IdExtractor idExtractor;

    private final ChatFacade chatFacade;

    @Autowired
    public ChatController(UsernameExtractor usernameExtractor, IdExtractor idExtractor, ChatFacade chatFacade) {
        this.usernameExtractor = usernameExtractor;
        this.idExtractor = idExtractor;
        this.chatFacade = chatFacade;
    }

    @PostMapping ("/create")
    @ResponseStatus (HttpStatus.CREATED)
    ChatModel createChat(@RequestBody ChatModel chatModel, Authentication authentication) {
        var tenantId = idExtractor.extract(authentication);
        chatModel.setTenantId(tenantId);
        return chatFacade.createChat(chatModel.getLandlordId(), chatModel.getTenantId());

    }

    @PostMapping ("")
    @ResponseStatus (HttpStatus.CREATED)
    void addMessage(@RequestBody Message message, Authentication authentication) {
        final var sentBy = usernameExtractor.extract(authentication);
        chatFacade.addMessage(message, sentBy);

    }

    @GetMapping ("/mychats")
    List<ChatListModel> getChats(Authentication authentication) {
        final var currentUser = usernameExtractor.extract(authentication);
        return chatFacade.getMyChats(currentUser);

    }

    @GetMapping ("/messages/{chatId}")
    List<Message> getChatMessages(@PathVariable ("chatId") long chatId) {
        return chatFacade.getChatMessages(chatId);

    }


}
