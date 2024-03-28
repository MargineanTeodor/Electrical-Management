import { User } from "../user/user";

export class Device {
    id: number | undefined;
    description: String | undefined;
    address: String |undefined;
    maximHrEnergyConsumption: number |undefined;
    user: User| undefined;
}