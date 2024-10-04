import React from 'react';

interface ConfirmDialogProps {
  message: string;
  onConfirm: () => void;
  onCancel: () => void;
  isActive: boolean;
}

const ConfirmDialog: React.FC<ConfirmDialogProps> = ({ message, onConfirm, onCancel, isActive }) => {
  return (
    <div className={`modal ${isActive ? 'is-active' : ''}`}>
      <div className="modal-background" onClick={onCancel}></div>
      <div className="modal-card">
        <header className="modal-card-head">
          <p className="modal-card-title">Confirm</p>
          <button className="delete" aria-label="close" onClick={onCancel}></button>
        </header>
        <section className="modal-card-body">
          <p>{message}</p>
        </section>
        <footer className="modal-card-foot">
          <button className="button is-danger" onClick={onConfirm}>Yes, Delete</button>
          <button className="button" onClick={onCancel}>Cancel</button>
        </footer>
      </div>
    </div>
  );
};

export default ConfirmDialog;