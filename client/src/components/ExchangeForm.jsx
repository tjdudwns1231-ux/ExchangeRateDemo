import { useEffect, useState } from "react";
import "./ExchangeForm.css";
import {getExchangeRate, calculateExchange,} from "../services/exchangeApi";

function formatNumber(value) { // 숫자를 통화 형식으로 포맷팅하는 함수
  if (value == null) {
    return "0.00";
  }

  return Number(value).toLocaleString("en-US", { // 숫자로 변환한 뒤 통화 형식으로 포맷팅
    minimumFractionDigits: 2, 
    maximumFractionDigits: 2, // 소수점 이하 2자리까지 표시
  });
}

function ExchangeForm() { // 환율 계산 폼 컴포넌트
  const [recipientCurrency, setRecipientCurrency] = useState("KRW"); //선택된 통화
  const [rate, setRate] = useState(null); // 현재 환율
  const [amount, setAmount] = useState(""); // 사용자가 입력한 송금액
  const [result, setResult] = useState(null); // 송금 계산 결과
  const [error, setError] = useState(""); // 계산 실패 시 보여줄 에러 메시지

  useEffect(() => { // 화면에서 사용자가 통화(recipientCurrency)를 변경할 때마다 환율을 조회
    async function fetchRate() { 
      try {
        const data = await getExchangeRate(recipientCurrency); //환율조회API 호출
        setRate(data.rate); // 조회된 환율을 setRate 상태에 저장
      } catch (error) {
        console.error(error);
      }
    }

    fetchRate();
  }, [recipientCurrency]); // recipientCurrency가 변경될 때마다 fetchRate 함수 호출 /  handleCurrencyChange(e)가 호출될 때마다 recipientCurrency가 변경되므로 useEffect가 실행됨

  async function handleSubmit(e) { // submit으로 폼 제출하면 호출되는 함수
    e.preventDefault(); // 폼 제출 시 페이지 새로고침 방지

    setError("");

    const parsedAmount =
      amount.trim() === "" ? null : Number(amount); // 사용자가 입력한 송금액을 숫자로 변환

    try {
      const data = await calculateExchange( // 송금 계산 API 호출
        recipientCurrency,
        parsedAmount
      );

      setResult(data); // 송금 계산 결과를 setResult 상태에 저장
    } catch (error) {
      setError(error.message);
      setResult(null);
    }
  }

  function handleCurrencyChange(e) { // 사용자가 통화를 변경하면 호출되는 함수
    setRecipientCurrency(e.target.value); 

    // 이전 통화의 계산 결과를 그대로 보여주지 않도록 초기화
    setResult(null);
    setError("");
  }

  return (
    <form
      className="exchange-form"
      onSubmit={handleSubmit} // 폼 제출 시 handleSubmit 함수 호출
    >
      <h1 className="exchange-form__title">환율 계산</h1>

      <div className="exchange-form__row">
        <label>송금국가</label>
        <p>미국 (USD)</p>
      </div>

      <div className="exchange-form__row">
        <label htmlFor="recipientCountry">수취국가</label>

        <select
          id="recipientCountry"
          value={recipientCurrency}
          onChange={handleCurrencyChange} // 사용자가 통화를 변경하면 handleCurrencyChange 함수 호출
        >
          <option value="KRW">한국 (KRW)</option>
          <option value="JPY">일본 (JPY)</option>
          <option value="PHP">필리핀 (PHP)</option>
        </select>
      </div>

      <div className="exchange-form__row">
        <label>환율</label>

        <p>
          1 USD = {formatNumber(rate)} {recipientCurrency} {/* // 조회된 환율을 통화 형식으로 포맷팅하여 보여줌 */}
        </p>
      </div>

      <div className="exchange-form__row">
        <label htmlFor="amount">송금액</label>

        <div className="exchange-form__amount">
          <input
            id="amount"
            type="text"
            placeholder="송금액을 입력하세요"
            value={amount}
            onChange={(e) => setAmount(e.target.value)} // 사용자가 송금액을 입력하면 setAmount 상태를 업데이트
          />

          <span>USD</span>
        </div>
      </div>

      <button
        className="exchange-form__button"
        type="submit"
      >
        Submit
      </button>

      {error && (
        <p className="exchange-form__error">
          {error}
        </p>
      )}

      <div className="exchange-form__result">
        <p>
          수취금액은{" "}
          {formatNumber(result?.receivedAmount)}{" "}
          {result?.currency ?? recipientCurrency} 입니다. {/* optional chaining을 사용하여 result가 null일 경우에도 오류 없이 "0.00"과 선택된 통화를 보여줌 */}
        </p>
      </div>
    </form>
  );
}

export default ExchangeForm;