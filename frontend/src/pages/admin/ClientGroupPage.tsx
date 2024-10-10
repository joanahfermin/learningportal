import React, { useState, useEffect } from 'react';
import ClientGroupService from '../../services/ClientGroupService';
import { ClientGroup } from '../../model/Models';
import ConfirmDialog from '../../components/ConfirmDialog'; // Update the path as needed

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';

const ClientGroupPage: React.FC = () => {

    const [clientGroups, setClientGroups] = useState<ClientGroup[]>([]);
    const [selectedClientGroup, setSelectedClientGroup] = useState<Partial<ClientGroup> | null>(null);
    const [isModalActive, setModalActive] = useState(false);
    const [isConfirmDelete, setIsConfirmDelete] = useState(false);
    const [clientGroupToDelete, setClientGroupToDelete] = useState<number | null>(null);

    useEffect(() => {
        fetchClientGroups();
    }, []);

    const fetchClientGroups = async () => {
        const data = await ClientGroupService.getAllClientGroups();
        setClientGroups(data);
    };

    const openModal = (clientGroup?: ClientGroup) => {
        setSelectedClientGroup(clientGroup || { name: '', description: '' });
        setModalActive(true);
    };

    const closeModal = () => {
        setModalActive(false);
        setSelectedClientGroup(null);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setSelectedClientGroup((prev) => ({ ...prev, [name]: value }));
    };

    const saveClientGroup = async () => {
        if (selectedClientGroup) {
            if (selectedClientGroup.id) {
                await ClientGroupService.updateClientGroup(selectedClientGroup.id, selectedClientGroup as ClientGroup);
            } else {
                await ClientGroupService.createClientGroup(selectedClientGroup as Omit<ClientGroup, 'id'>);
            }
            closeModal();
            fetchClientGroups();
        }
    };

    const requestDeleteClientGroup = (id: number) => {
        setIsConfirmDelete(true);
        setClientGroupToDelete(id);
    };

    const confirmDeleteClientGroup = async () => {
        if (clientGroupToDelete !== null) {
            await ClientGroupService.deleteClientGroup(clientGroupToDelete);
            setIsConfirmDelete(false);
            setClientGroupToDelete(null);
            fetchClientGroups();
        }
    };

    const cancelDeleteClientGroup = () => {
        setIsConfirmDelete(false);
        setClientGroupToDelete(null);
    };

    return (
        <div className="container">
            <h1 className="title">Client Groups</h1>
            <div className="buttons">
                <button className="button is-primary" onClick={() => openModal()}>Add Client Group</button>
            </div>

            <table className="table is-fullwidth mt-4">
                <thead>
                    <tr>
                        <th style={{ width: 250 }}>Name</th>
                        <th>Description</th>
                        <th style={{ width: 200 }}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {clientGroups.map(clientGroup => (
                        <tr key={clientGroup.id}>
                            <td>{clientGroup.name}</td>
                            <td>{clientGroup.description}</td>
                            <td>
                                <button className="button is-small is-info mr-2" onClick={() => openModal(clientGroup)} title="Edit">
                                    <FontAwesomeIcon icon={faEdit} />
                                </button>
                                <button className="button is-small is-danger" onClick={() => requestDeleteClientGroup(clientGroup.id)} title="Delete">
                                    <FontAwesomeIcon icon={faTrash} />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {isModalActive && selectedClientGroup && (
                <div className={`modal ${isModalActive ? 'is-active' : ''}`}>
                    <div className="modal-background" onClick={closeModal}></div>
                    <div className="modal-card">
                        <header className="modal-card-head">
                            <p className="modal-card-title">{selectedClientGroup.id ? 'Edit Client Group' : 'Add Client Group'}</p>
                            <button className="delete" aria-label="close" onClick={closeModal}></button>
                        </header>
                        <section className="modal-card-body">
                            <div className="field">
                                <label className="label">Name</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="name"
                                        value={selectedClientGroup.name || ''}
                                        onChange={handleInputChange}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Description</label>
                                <div className="control">
                                    <textarea
                                        className="textarea"
                                        name="description"
                                        value={selectedClientGroup.description || ''}
                                        onChange={handleInputChange}
                                    ></textarea>
                                </div>
                            </div>
                        </section>
                        <footer className="modal-card-foot">
                            <button className="button is-success" onClick={saveClientGroup}>Save changes</button>
                            <button className="button" onClick={closeModal}>Cancel</button>
                        </footer>
                    </div>
                </div>
            )}

            <ConfirmDialog
                message="Are you sure you want to delete this client group?"
                isActive={isConfirmDelete}
                onConfirm={confirmDeleteClientGroup}
                onCancel={cancelDeleteClientGroup}
            />
        </div>
    );
};

export default ClientGroupPage;
