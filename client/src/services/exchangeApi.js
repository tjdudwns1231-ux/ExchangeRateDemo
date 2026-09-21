export async function getExchangeRate(currency) { // 환율 조회 API 호출
  const response = await fetch(
    `http://localhost:8080/api/exchange-rates/${currency}`
  );

  const data = await response.json(); // 응답을 JSON 형식으로 변환하여 data 변수에 저장

  if (!response.ok) { // 응답이 정상적이지 않으면 에러를 발생시킨다.
    throw new Error(
      data.message || "환율 조회에 실패했습니다."
    );
  }

  return data;
}

export async function calculateExchange(currency, amount) { // 송금 계산 API 호출
  const response = await fetch(
    "http://localhost:8080/api/exchange",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // Spring에게 요청 본문이 JSON 형식임을 명시
      },
      body: JSON.stringify({ // 요청 본문에 통화와 송금액을 JSON 형식으로 변환해서 전달
        currency,
        amount,
      }),
    }
  );

  const data = await response.json(); 

  if (!response.ok) { // 응답이 정상적이지 않으면 에러를 발생시킨다.
    throw new Error(
      data.message || "송금 계산에 실패했습니다."
    );
  }

  return data;
}