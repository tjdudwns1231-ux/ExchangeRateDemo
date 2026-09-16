package com.example.server.dto;
import java.math.BigDecimal;
import java.util.Map;

public class CurrencyLayerResponse { //CurrencyLayerResponse 클래스는 외부 환율 API가 보내준 응답 데이터를 매핑하기 위한 DTO(Data Transfer Object) 클래스이다. 
                                    //외부 API에서 반환되는 JSON 데이터를 이 클래스의 필드에 매핑하여 사용한다.

    private String source; // 외부 API에서 반환되는 환율 정보의 기준 통화를 나타내는 필드. 예를 들어, "USD"와 같은 값이 될 수 있다.
    private Map<String, BigDecimal> quotes; // 외부 API에서 반환되는 환율 정보를 나타내는 필드. Map<String, BigDecimal> 타입을 사용하여 통화 코드와 해당 통화의 환율을 매핑한다.
    private Boolean success; // 외부 API 호출이 성공했는지 여부를 나타내는 필드. true이면 성공, false이면 실패를 의미한다.
    private CurrencyLayerError error; // 외부 API 호출이 실패했을 경우, 실패에 대한 정보를 담는 필드. CurrencyLayerError 클래스는 실패에 대한 상세 정보를 담기 위한 DTO 클래스이다.

    public String getSource() { // source 필드의 값을 반환하는 getter 메서드. 외부 API에서 반환된 기준 통화를 가져올 때 사용된다.
        return source;
    }

    public void setSource(String source) { // source 필드의 값을 설정하는 setter 메서드. 외부 API에서 반환된 기준 통화를 설정할 때 사용된다.
        this.source = source;
    }

    public Map<String, BigDecimal> getQuotes() { // quotes 필드의 값을 반환하는 getter 메서드. 외부 API에서 반환된 환율 정보를 가져올 때 사용된다.
        return quotes;
    }

    public void setQuotes(Map<String, BigDecimal> quotes) { // quotes 필드의 값을 설정하는 setter 메서드. 외부 API에서 반환된 환율 정보를 설정할 때 사용된다.
        this.quotes = quotes;
    }

    public Boolean getSuccess() { // success 필드의 값을 반환하는 getter 메서드. 외부 API 호출이 성공했는지 여부를 가져올 때 사용된다.
        return success;
    }

    public void setSuccess(Boolean success) { // success 필드의 값을 설정하는 setter 메서드. 외부 API 호출이 성공했는지 여부를 설정할 때 사용된다.
        this.success = success;
    }

    public CurrencyLayerError getError() { // error 필드의 값을 반환하는 getter 메서드. 외부 API 호출이 실패했을 경우, 실패에 대한 정보를 가져올 때 사용된다.
        return error;
    }

    public void setError(CurrencyLayerError error) { // error 필드의 값을 설정하는 setter 메서드. 외부 API 호출이 실패했을 경우, 실패에 대한 정보를 설정할 때 사용된다.
        this.error = error;
    }

}
