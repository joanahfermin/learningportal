import axiosInstance from "../api/axiosConfig";
import { ClientGroup } from "../model/Models";

class ClientGroupService {
    // Fetch all client groups
    async getAllClientGroups(): Promise<ClientGroup[]> {
        const response = await axiosInstance.get<ClientGroup[]>("/clientGroups");
        return response.data;
    }

    // Fetch a client group by id
    async getClientGroupById(id: number): Promise<ClientGroup> {
        const response = await axiosInstance.get<ClientGroup>(`/clientGroups/${id}`);
        return response.data;
    }

    // Create a new client group
    async createClientGroup(clientGroup: Omit<ClientGroup, "id">): Promise<ClientGroup> {
        const response = await axiosInstance.post<ClientGroup>("/clientGroups", clientGroup);
        return response.data;
    }

    // Update a client group by id
    async updateClientGroup(id: number, clientGroup: Partial<ClientGroup>): Promise<ClientGroup> {
        const response = await axiosInstance.put<ClientGroup>(`/clientGroups/${id}`, clientGroup);
        return response.data;
    }

    // Delete a client group by id
    async deleteClientGroup(id: number): Promise<void> {
        await axiosInstance.delete(`/clientGroups/${id}`);
    }
}

export default new ClientGroupService();
