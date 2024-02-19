import React from 'react';

const HelloWorld = () => {
  var cardNumber , setCardNumber, cardHolder, setCardHolder, expiryDate, setCvv, setExpiryDate, handlePay, totalPrice, cvv;
  return (
    <div>
      <h1>Hello, World!</h1>
      <div className="modal fade" id="paymentModal" tabIndex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
      <div className="modal-dialog modal-dialog-centered">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="paymentModalLabel">Payment Details</h5>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div className="modal-body">
            <div className="mb-3">
              <label htmlFor="cardNumber" className="form-label">Card Number</label>
              <input type="text" className="form-control" id="cardNumber" value={cardNumber} onChange={(e) => setCardNumber(e.target.value)} />
            </div>
            <div className="mb-3">
              <label htmlFor="cardHolder" className="form-label">Card Holder</label>
              <input type="text" className="form-control" id="cardHolder" value={cardHolder} onChange={(e) => setCardHolder(e.target.value)} />
            </div>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="expiryDate" className="form-label">Expiry Date</label>
                <input type="text" className="form-control" id="expiryDate" value={expiryDate} onChange={(e) => setExpiryDate(e.target.value)} />
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="cvv" className="form-label">CVV</label>
                <input type="text" className="form-control" id="cvv" value={cvv} onChange={(e) => setCvv(e.target.value)} />
              </div>
            </div>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" className="btn btn-primary" onClick={handlePay}>Pay ${totalPrice}</button>
          </div>
        </div>
      </div>
    </div>
    </div>
  );
};

export default HelloWorld;
