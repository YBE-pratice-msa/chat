package com.example.chat.controller;

import com.example.chat.dto.ChatMessageDto;
import com.example.chat.pubsub.RedisPublisher;
import com.example.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        if (ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }

    /**
     * @MessageMapping("chat/message")은 스프링 프레임워크에서
     * WebSocket 또는 STOMP 기반의 메시징을 처리하기 위한 주석(annotation)이다.
     * @MessageMapping 을 통해 WebSocket 으로 들어오는 메시지 발행을 처리한다.
     * 클라이언트는 prefix 를 붙여서 /pub/chat/message 로 발행 요청하면 컨트롤러가 처리한다.
     * 메시지가 발행되면, /sub/chat/room/{chatRoomId} 로 메시지 send 하는데,
     * 이는 클라이언트에서 해당 주소 (/sub/chat/room/{chatRoomId})를 구독(sub) 하고 있다가,
     * 메시지가 전달되면 화면에 출력된다.
     * 여기서 (/sub/chat/room/{chatRoomId}) 는 채팅룸을 구분하는 값. 즉 pub/sub의 Topic 역할이다.
     * 기존의 WebSocketHandler 의 역할을 대체하므로 삭제한다.
     */

//    @PostMapping
//    public ChatRoom createRoom(@RequestParam String name) {
//        return chatService.createRoom(name);
//    }
//
//    @GetMapping
//    public List<ChatRoom> findAllRoom() {
//        return chatService.findAllRoom();
//    }
}

