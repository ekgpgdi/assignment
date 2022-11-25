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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class AssignmentService {

    public AssignmentResponseDto getResponse(AssignmentRequestDto assignmentRequestDto) {
        String webData = webScraping(assignmentRequestDto.getUrl(), assignmentRequestDto.getType());
        webData = removeOtherCharacter(webData);
        webData = orderData(webData);

        return new AssignmentResponseDto();
    }

    /**
     * 1. 받아온 url 을 이용하여 웹 스크래핑 진행
     * - 만약 url 이 옳지 않은 경우 -> 404 NOT FOUND URL exception throw
     * 2. type 에 따라 받아온 데이터 가공
     * - type 이 html 인 경우 : 받아온 Document 전체를 String 형태로 return
     * - type 이 text 인 경우 : Document 내 html tag 를 모두 제거하고 순수 문자열만 return
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
     * 영어 숫자만 남기고 제거
     *
     * @param webData
     * @return
     */
    public String removeOtherCharacter(String webData) {
        String match = "[^0-9a-zA-Z]";
        return webData.replaceAll(match, "");
    }

    /**
     * 문자열 내림차순 정렬
     *
     * @param str
     * @return
     */
    public String order(String str) {
        return Stream.of(str.split(""))
                .sorted()
                .collect(Collectors.joining());
    }

    /**
     * 출력되지 못한 남은 문자열과 숫자 문자열을 비교하여 교차 출력 진행
     *
     * @param index
     * @param str
     * @param numberIndex
     * @param number
     * @return
     */
    public String remnantStr(int index, String str, int numberIndex, String number) {
        StringBuilder mergeStr = new StringBuilder();

        while (index <= str.length() - 1) {
            if (numberIndex <= number.length() - 1) {
                mergeStr.append(number.charAt(numberIndex));
                numberIndex++;
            }

            mergeStr.append(str.charAt(index));
            index++;
        }

        return mergeStr.toString();
    }

    /**
     * 대문자 문자열과 소문자 문자열, 숫자 문자열을 비교하여 교차 출력 진행
     *
     * @param upperEnglish
     * @param lowerEnglish
     * @param number
     * @return
     */
    public String merge(String upperEnglish, String lowerEnglish, String number) {
        StringBuilder mergeStr = new StringBuilder();
        int lowerEnglishIndex = 0;
        int upperEnglishIndex = 0;
        int numberIndex = 0;

        if (lowerEnglish.length() == 0) {
            return upperEnglish;
        } else if (upperEnglish.length() == 0) {
            return lowerEnglish;
        }

        while (lowerEnglishIndex + upperEnglishIndex < lowerEnglish.length() + upperEnglish.length()) {
            if (Character.toUpperCase(lowerEnglish.charAt(lowerEnglishIndex)) < upperEnglish.charAt(upperEnglishIndex)) {
                mergeStr.append(lowerEnglish.charAt(lowerEnglishIndex));
                if (lowerEnglishIndex < lowerEnglish.length() - 1) {
                    lowerEnglishIndex++;
                } else {
                    lowerEnglishIndex = 0;
                    break;
                }
            } else {
                mergeStr.append(upperEnglish.charAt(upperEnglishIndex));
                if (upperEnglishIndex < upperEnglish.length() - 1) {
                    upperEnglishIndex++;
                } else {
                    upperEnglishIndex = 0;
                    break;
                }
            }

            if (numberIndex <= number.length() - 1) {
                mergeStr.append(number.charAt(numberIndex));
                numberIndex++;
            }
        }

        // 대문자의 문자열이 비어 반복이 종료된 경우
        if (upperEnglishIndex == 0) {
            // 남은 소문자 문자열과 숫자 문자열을 교차 출력 진행
            mergeStr.append(remnantStr(lowerEnglishIndex, lowerEnglish, numberIndex, number));
        } else {
            // 남은 대문자 문자열과 숫자 문자열을 교차 출력 진행
            mergeStr.append(remnantStr(upperEnglishIndex, upperEnglish, numberIndex, number));
        }

        // 모든 반복이 끝난 후에도 남아있는 숫자 문자열이 있다면 문자열 끝에 추가
        if (numberIndex <= number.length() - 1) {
            mergeStr.append(number.substring(numberIndex + 1));
        }

        return mergeStr.toString();
    }

    /**
     * 하나의 문자열을 대문자 문자열, 소문자 문자열, 숫자 문자열로 분리
     *
     * @param webData
     * @return
     */
    public String orderData(String webData) {
        String upperEnglish = order(webData.replaceAll("[^A-Z]", ""));
        String lowerEnglish = order(webData.replaceAll("[^a-z]", ""));
        String number = order(webData.replaceAll("[^0-9]", ""));

        return merge(upperEnglish, lowerEnglish, number);
    }

}
