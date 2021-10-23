import { Status } from "./status";
import { User } from "../user/user";

export class Occasion {
    id!: number;
    user!: User;
    status!: Status
    timestamp!: Date;
}
