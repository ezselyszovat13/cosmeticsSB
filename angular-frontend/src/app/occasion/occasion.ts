import { Status } from "./status";
import { User } from "../user/user";
import { Treatment } from "../treatment/treatment";

export class Occasion {
    id!: number;
    user!: User;
    status!: Status
    timestamp!: Date;
    treatments!: Treatment[]
}
