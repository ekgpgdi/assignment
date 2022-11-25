package com.dahye.assignment.service;

import com.dahye.assignment.dto.AssignmentRequestDto;
import com.dahye.assignment.dto.AssignmentResponseDto;
import com.dahye.assignment.exception.ErrorCode;
import com.dahye.assignment.exception.NotFoundUrlException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AssignmentService {

    public AssignmentResponseDto getResponse(AssignmentRequestDto assignmentRequestDto) {
        String webData = webScraping(assignmentRequestDto.getUrl(), assignmentRequestDto.getType());
        String oneLineData = removeEnter(webData);

        return new AssignmentResponseDto();
    }

    /**
     * 1. 받아온 url 을 이용하여 웹 스크래핑 진행
     *      - 만약 url 이 옳지 않은 경우 -> 404 NOT FOUND URL exception throw
     * 2. type 에 따라 받아온 데이터 가공
     *      - type 이 html 인 경우 : 받아온 Document 전체를 String 형태로 return
     *      - type 이 text 인 경우 : Document 내 html tag 를 모두 제거하고 순수 문자열만 return
     *
     * @param url
     * @param type
     * @return
     */
    public String webScraping(String url, String type) {
        Document doc;
        try {
            doc = Jsoup.connect(url).timeout(50000).get();
        } catch (IOException e) {
            log.error("스크래핑 오류: " + e);
            throw new NotFoundUrlException("not found url", ErrorCode.NOT_FOUND_URL);
        }

        if (type.equals("html")) {
            return doc.toString();
        } else {
            return doc.text();
        }
    }

    /**
     * 스크래핑 된 데이터의 줄바꿈을 모두 제거
     * 
     * @param webData
     * @return
     */
    public String removeEnter(String webData) {
        return webData.replace("\n", "").replace("\r", "");
    }

}
