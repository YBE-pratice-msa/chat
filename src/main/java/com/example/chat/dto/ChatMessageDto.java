package com.example.chat.dto;

import com.example.chat.entity.ChatMessage;
import com.example.chat.type.MessageType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    // 메시지  타입 : 입장, 채팅

    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private String sender; // 채팅을 보낸 사람
    private String message; // 메시지

    public static ChatMessageDto fromEntity(ChatMessage chatMessage) {
        return ChatMessageDto.builder()
                .type(chatMessage.getType())
                .sender(chatMessage.getSender())
                .roomId(chatMessage.getRoomId())
                .message(chatMessage.getMessage())
                .build();
    }

}
