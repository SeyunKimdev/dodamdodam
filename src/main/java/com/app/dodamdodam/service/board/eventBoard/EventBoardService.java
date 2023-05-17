package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.file.EventFileRepository;
import jdk.jfr.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EventBoardService {

//    상세보기
    public EventBoardDTO getDetail(Long id);

    // 저장
    public void write(EventBoardDTO eventBoardDTO, Long memberId);

    //    현재 시퀀스 가져오기
    public EventBoard getCurrentSequence();

    //    목록 페이징(최신순)
    public Slice<EventBoardDTO> getEventBoards(Pageable pageable);

    default EventBoardDTO eventBoardToDTO(EventBoard eventBoard){
        return EventBoardDTO.builder()
                .id(eventBoard.getId())
                .boardTitle(eventBoard.getBoardTitle())
                .boardContent(eventBoard.getBoardContent())
                .memberDTO(toMemberDTO(eventBoard.getMember()))
                .eventFiles(eventFileToDTO(eventBoard.getEventFiles()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPhone(member.getMemberPhone())
                .address(member.getAddress())
                .memberStatus(member.getMemberStatus())
                .memberPoint(member.getMemberPoint())
                .participationCount(member.getParticipationCount())
                .recruitmentedCount(member.getParticipationCount())
                .createdDate(member.getCreatedDate())
                .build();
    }

    default EventBoard toEventBoardEntity(EventBoardDTO eventBoardDTO){
        return EventBoard.builder()
                .id(eventBoardDTO.getId())
                .boardTitle(eventBoardDTO.getBoardTitle())
                .boardContent(eventBoardDTO.getBoardContent())
                .eventFiles(eventBoardDTO.getEventFiles().stream().map(file -> toEventFileEntity(file)).collect(Collectors.toList()))
                .member(toMemberEntity(eventBoardDTO.getMemberDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberPhone(memberDTO.getMemberPhone())
                .address(memberDTO.getAddress())
                .memberStatus(memberDTO.getMemberStatus())
                .memberRole(memberDTO.getMemberRole())
                .build();
    }

    default List<EventFileDTO> eventFileToDTO(List<EventFile> eventFiles){
        List<EventFileDTO> eventFileDTOS = new ArrayList<>();
        eventFiles.forEach(
            eventFile ->{
                EventFileDTO eventFileDTO = EventFileDTO.builder()
                        .id(eventFile.getId())
                        .fileOriginalName(eventFile.getFileOriginalName())
                        .filePath(eventFile.getFilePath())
                        .fileUuid(eventFile.getFileUuid())
                        .fileSize(eventFile.getFileSize())
                        .fileRepresent(eventFile.getFileRepresent())
                        .build();
                eventFileDTOS.add(eventFileDTO);
            }
        );
        return eventFileDTOS;
    }

    default EventFile toEventFileEntity(EventFileDTO eventFileDTO){
        return EventFile.builder()
                .id(eventFileDTO.getId())
                .fileOriginalName(eventFileDTO.getFileOriginalName())
                .filePath(eventFileDTO.getFilePath())
                .fileRepresent(eventFileDTO.getFileRepresent())
                .fileSize(eventFileDTO.getFileSize())
                .fileUuid(eventFileDTO.getFileUuid())
                .build();
    }

}